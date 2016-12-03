package com.br.gc.pds.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RotasController {
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(){
		return "login";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String login(){
		
		return "login";
	}
	
	@RequestMapping(value="/error", method= RequestMethod.GET)
	public String loginError(Model model){
		model.addAttribute("loginError", true);
		return "login-error";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(){
		return "logout";
	}
	
	@RequestMapping(value="/home", method= RequestMethod.GET)
	public String home(){
		return "home";
	}
}
