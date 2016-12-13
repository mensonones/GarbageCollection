package com.br.gc.pds.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.gc.pds.factory.ColetaFactory;
import com.br.gc.pds.factory.RotaFactory;
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
import com.br.gc.pds.util.DadosPDF;
import com.br.gc.pds.util.MapStatusColeta;
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
	private ColetaFactory coletaFactory;
	private ColetaEntity coleta;
	private Proxy proxy;
	private RotaFactory rotaFactory;

	@RequestMapping(value ="/alterarStatusColeta/{coleta_id}+{status}", method = RequestMethod.GET)
	public String alterarStatusColeta(@PathVariable("coleta_id") Long id,
			@PathVariable("status") String status) throws InvalidProtocolBufferException {
		proxy = new Proxy();
		ColetaEntity coleta = coletaService.buscarColeta(id);
		MapStatusColeta map = new MapStatusColeta();

		ModificadorStatusColeta modificador = (ModificadorStatusColeta) map.getInstance(status);
		ColetaEntity coletaAtualizada = modificador.atulaizarStatusColeta(proxy, coleta);
		coletaService.salvar(coletaAtualizada);
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

	@RequestMapping(value = "/listarCaminhao", method = RequestMethod.GET)
	public String listarCaminhao(Model model) {
		List<Caminhao> listaCaminhao = caminhaoService.listarCaminhao();
		model.addAttribute("listaCaminhao", listaCaminhao);
		model.addAttribute("livre", StatusCaminhaoColeta.LIVRE);
		return "caminhao/listaCaminhao";
	}

	@RequestMapping(value = "/gerarColeta/{id_caminhao}", method = RequestMethod.GET)
	public String gerarColeta(@PathVariable Long id_caminhao, Model model) throws InvalidProtocolBufferException {
		proxy = new Proxy();
		rotaFactory = new RotaFactory();
		List<String> localizacoesLixeiras = new ArrayList<String>();
		List<Lixeira> lixeiras = proxy.buscarLixeira();

		Caminhao caminhao = caminhaoService.buscarCaminhao(id_caminhao);
		localizacoesLixeiras.add(ConstantesPontosRota.EMPRESA);
		coletaFactory = new ColetaFactory();

		coleta = coletaFactory.factoryLixeirasColeta(lixeiras, caminhao);

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
		List<String> rotaLixeiras = rotaFactory.gerarRota(localizacoesLixeiras);

		coletaService.salvar(coleta);

		List<Rota> melhorRota = proxy.calcularRota(rotaLixeiras, coleta.getLixeiras());

		DadosPDF pdf = new DadosPDF();
		pdf.gerarRelatorioRota(coleta, melhorRota);

		model.addAttribute("rotas", melhorRota);
		return "rota/verRota";
	}
}
