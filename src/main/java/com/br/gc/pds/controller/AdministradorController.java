package com.br.gc.pds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.gc.pds.model.Administrador;
import com.br.gc.pds.repository.AdministradorRepository;

@Controller
public class AdministradorController {
	
	@Autowired
	AdministradorRepository admRepository;
	
	@RequestMapping("/home")
	public String home(Model model){
		return "home";
	}
	
	@RequestMapping("/adicionarAdm")
	public String adicionarAdm(Model model){
		model.addAttribute("adm", new Administrador());
		return "adm/adicionarAdm";
	}
	
	@RequestMapping("/listarAdm")
	public String listarAdm(Model model){
		model.addAttribute("admList", admRepository.findAll());
		return "adm/listarAdm";
	}
}