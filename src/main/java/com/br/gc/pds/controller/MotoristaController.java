package com.br.gc.pds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.gc.pds.model.Motorista;
import com.br.gc.pds.repository.MotoristaRepository;

@Controller
public class MotoristaController {
	
	@Autowired
	MotoristaRepository motoristaRepository;
	
	@RequestMapping("/home")
	public String home(Model model){
		return "home";
	}
	
	@RequestMapping("/adicionarMotoristaForm")
	public String adicionarMotoristaForm(Model model){
		model.addAttribute("motorista",new Motorista());
		return "motorista/adicionarMotorista";
	}
	
	@RequestMapping(value="/adicionarMotorista",method=RequestMethod.POST)
	public String adicionarMotorista(@ModelAttribute Motorista motorista){
		motoristaRepository.save(motorista);
		return "redirect:home";
	}
	
	@RequestMapping("/listarMotoristas")
	public String listarMottoristas(Model model){
		model.addAttribute("motoristaList",motoristaRepository.findAll());
		return "motorista/listarMotoristas";
	}
}
