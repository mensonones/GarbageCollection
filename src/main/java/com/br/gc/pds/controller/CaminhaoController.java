package com.br.gc.pds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.gc.pds.model.Caminhao;
import com.br.gc.pds.service.CaminhaoService;
import com.br.gc.pds.util.StatusCaminhaoCapacidade;
import com.br.gc.pds.util.StatusCaminhaoColeta;

@Controller
public class CaminhaoController {
	
	@Autowired
	CaminhaoService caminhaoService;
	
	@RequestMapping("/cadastrarCaminhaoForm")
	public String cadastrarCaminhaoForm(Model model){
		Caminhao caminhao = new Caminhao();
		model.addAttribute("caminhao",caminhao);
		return"caminhao/cadastroCaminhao";
	}
	
	@RequestMapping(value = "/cadastrarCaminhao")
	public String cadastrarCaminhao(@ModelAttribute Caminhao caminhao){
		caminhao.setStatusCaminhaColeta(StatusCaminhaoColeta.LIVRE);
		caminhao.setStatusCaminhaoCapacidade(StatusCaminhaoCapacidade.VAZIO);
		caminhaoService.cadastrarCaminhao(caminhao);
		return "redirect:/home";
	}
	
	@RequestMapping(value="/excluirCaminhao/{id_caminhao}")
	public String excluirCaminhao(@PathVariable("id_caminhao") Long id_caminhao){
		Caminhao caminhao = caminhaoService.buscarCaminhao(id_caminhao);
		caminhaoService.deletarCaminhao(caminhao);
		return"redirect:/listarCaminhao";
	}
	
	@RequestMapping(value = "/listarCaminhao", method = RequestMethod.GET)
	public String listarCaminhao(Model model) {
		List<Caminhao> listaCaminhao = caminhaoService.listarCaminhao();
		model.addAttribute("listaCaminhao", listaCaminhao);
		model.addAttribute("livre", StatusCaminhaoColeta.LIVRE);
		return "caminhao/listaCaminhao";
	}	
}
