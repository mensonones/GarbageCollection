package com.br.gc.pds.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.gc.pds.factory.ColetaFactory;
import com.br.gc.pds.factory.Factory;
import com.br.gc.pds.factory.GeradorRota;
import com.br.gc.pds.model.Caminhao;
import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.model.LixeiraEntity;
import com.br.gc.pds.model.Lixeiras.Lixeira;
import com.br.gc.pds.model.Rotas.Rota;
import com.br.gc.pds.net.Proxy;
import com.br.gc.pds.service.CaminhaoService;
import com.br.gc.pds.service.ColetaService;
import com.br.gc.pds.service.LixeiraService;
import com.br.gc.pds.service.ModificadorStatusColeta;
import com.br.gc.pds.util.ConstantesPontosRota;
import com.br.gc.pds.util.MapStatusColeta;
import com.br.gc.pds.util.RelatorioRota;
import com.br.gc.pds.util.StatusCaminhaoColeta;
import com.br.gc.pds.util.ValorStatusColeta;
import com.google.protobuf.InvalidProtocolBufferException;

@Controller
public class ColetaController {

	@Autowired
	ColetaService coletaService;

	@Autowired
	CaminhaoService caminhaoService;
	@Autowired
	LixeiraService lixeiraService;
	private Factory factory;
	private ColetaEntity coleta;
	private Proxy proxy;
	private GeradorRota geradorRota;

	@RequestMapping(value ="/alterarStatusColeta/{coleta_id}+{status}")
	public String alterarStatusColeta(@PathVariable("coleta_id") Long id,
			@PathVariable("status") String status) throws InvalidProtocolBufferException {
		proxy = new Proxy();
		ColetaEntity coleta = coletaService.buscarColeta(id);
		MapStatusColeta map = new MapStatusColeta();

		ModificadorStatusColeta modificador = (ModificadorStatusColeta) map.getInstance(status);
		modificador.atulaizarStatusColeta(proxy,coleta,coletaService);
		return "redirect:/listarColeta";
	}

	@RequestMapping(value ="/listarColeta", method = RequestMethod.GET)
	public String listarColeta(Model model) {
		
		List<ColetaEntity> coletas = coletaService.listar();
		
		
		model.addAttribute("coletas", coletas);
		model.addAttribute("andamento", ValorStatusColeta.ANDAMENTO);
		model.addAttribute("completo", ValorStatusColeta.COMPLETO);
		model.addAttribute("cancelado", ValorStatusColeta.CANCELADO);
		return "coleta/listarColeta";
	}

	@RequestMapping(value = "/gerarColeta/{id_caminhao}", method = RequestMethod.GET)
	public String gerarColeta(@PathVariable Long id_caminhao, Model model) throws InvalidProtocolBufferException {
		proxy = new Proxy();
		geradorRota = new GeradorRota();
		Date data = new Date();
		RelatorioRota relatorio = new RelatorioRota();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<String> localizacoesLixeiras = new ArrayList<String>();
		List<Lixeira> lixeiras = proxy.buscarLixeira();

		Caminhao caminhao = caminhaoService.buscarCaminhao(id_caminhao);
		localizacoesLixeiras.add(ConstantesPontosRota.EMPRESA);
		factory = new ColetaFactory(lixeiras,caminhao);

		factory.factory();
		coleta = (ColetaEntity) factory.getFactory();

		if (coleta.getLixeiras().isEmpty()) {
			return "login-error";
		}

		caminhao.setStatusCaminhaColeta(StatusCaminhaoColeta.ROTA);
		coleta.setStatusColeta(ValorStatusColeta.ANDAMENTO);
		coleta.setCaminhao(caminhao);
		
		for (LixeiraEntity lixeira : coleta.getLixeiras()) {
			lixeiraService.salvar(lixeira);
			localizacoesLixeiras.add(lixeira.getLocalizacao());
		}
		
		List<String> rotaLixeiras = geradorRota.gerarRota(localizacoesLixeiras);
		coleta.setDataColeta(formatador.format(data));
		coletaService.salvar(coleta);

		List<Rota> melhorRota = proxy.calcularRota(rotaLixeiras, coleta.getLixeiras());
		relatorio.gerarRelatorioRota(coleta, melhorRota);
		model.addAttribute("rotas", melhorRota);
		return "rota/verRota";
	}
	
	@RequestMapping(value="/relatorioRota-{id}+{opcao}")
	public void relatorioRota(@PathVariable("id") Long id,@PathVariable("opcao") String opcao ,HttpServletResponse response) throws IOException{
		String path = "C:/Users/Carlos/git/GarbageCollection/src/main/resources/relatorios/coletas/Coleta-"+id+".PDF";
		if(opcao.equals("visualizar")){
			Path file = Paths.get(path);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline;filename=Coleta-"+id+".PDF");
			Files.copy(file,response.getOutputStream());
			response.getOutputStream().flush();
		}else{
			Path file = Paths.get(path);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment;filename=Coleta-"+id+".PDF");
			Files.copy(file,response.getOutputStream());
			response.getOutputStream().flush();

		}
		
	}
	
	@RequestMapping(value="/verRelatorio" , method=RequestMethod.GET)
	public String verRelatorio(Model model) throws IOException{
		List<ColetaEntity> coletas = coletaService.listar();
		model.addAttribute("coletas", coletas);
		model.addAttribute("completo", ValorStatusColeta.COMPLETO);
		return"relatorio/verRelatorio";
	}
}
