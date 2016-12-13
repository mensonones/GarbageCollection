package com.br.gc.pds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping(value = "/cadastrarCaminhao", method = RequestMethod.POST)
	public String cadastrarCaminhao(@ModelAttribute Caminhao caminhao){
		caminhao.setStatusCaminhaColeta(StatusCaminhaoColeta.LIVRE);
		caminhao.setStatusCaminhaoCapacidade(StatusCaminhaoCapacidade.VAZIO);
		caminhaoService.cadastrarCaminha(caminhao);
		return "redirect:/home";
	}
		
}
