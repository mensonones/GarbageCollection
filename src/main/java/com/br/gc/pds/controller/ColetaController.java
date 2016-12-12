package com.br.gc.pds.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

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
import com.google.protobuf.InvalidProtocolBufferException;

@Controller
public class ColetaController {
	
	@Autowired
	ColetaService coletaService;
	
	@Autowired
	CaminhaoService caminhaoService;
	private ColetaFactory coletaFactory;
	private Proxy proxy;
	private RotaFactory rotaFactory;
	
	//Metodo Responsavel por Marcar como Finalizada a coleta alterando StatusColeta das lixeiras para LIVRE e o StatusCapacida para VAZIO
	@RequestMapping(value="/concluir/{coleta_id}",method = RequestMethod.GET)
	public String concluirColeta(@PathVariable("coleta_id") Long id){
		proxy = new Proxy();
		ColetaEntity coleta = coletaService.buscarColeta(id);
		List<String> pontosAlterarStatus = new ArrayList<>();
		for(LixeiraEntity l : coleta.getLixeiras()){
			pontosAlterarStatus.add(String.valueOf(l.getId()));
		}
		
		try {
			proxy.alterarStatusColeta("1", pontosAlterarStatus);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		
		coletaService.deletarColeta(coleta);
		return "coletas";
	}
	
	//Metodo Responsavel por Listar Todas As Coletas
	/*@RequestMapping(method=RequestMethod.GET)
	public String listarColetas(Model model){
		List<ColetaEntity> coletas = coletaService.listar();
		model.addAttribute("coletas", coletas);
		return "coletas";
	}*/

	@RequestMapping(value="/listarCaminhao", method = RequestMethod.GET)
	public String listarCaminhao(Model model){
		List<Caminhao> listaCaminhao = caminhaoService.listarCaminhao();
		model.addAttribute("listaCaminhao",listaCaminhao);
		return "caminhao/listaCaminhao";
	}
	
	@RequestMapping(value="/gerarColeta/{id_caminhao}", method= RequestMethod.GET)
	public String gerarColeta(@PathVariable Long id_caminhao,Model model) throws InvalidProtocolBufferException{
		proxy = new Proxy();
		rotaFactory = new RotaFactory();
		
		//Buscar Lista de Lixeiras no servidor
		List<Lixeira> lixeiras = proxy.buscarLixeira();
		
		for(Lixeira l : lixeiras){
			System.out.println(l.getLocalizacao());
		}
		
		//Buscar Caminhao que irá fazer a coleta
		Caminhao caminhao = caminhaoService.buscarCaminhao(id_caminhao);
		
		//Cria a coleta
		coletaFactory = new ColetaFactory();
		ColetaEntity coleta = coletaFactory.factoryColeta(lixeiras, caminhao);
		if(coleta != null){
			
			List<LixeiraEntity> rotaLixeiras = rotaFactory.gerarRota(coleta.getLixeiras());
			List<Rota> melhorRota = proxy.calcularRota(rotaLixeiras);
			
			//Criar PDF Com a Rota
			//Salvar no banco
			//coletaService.salvar(coleta);
			model.addAttribute("rotas", melhorRota);
		} 
		
		return "rota/verRota";
	}
}
