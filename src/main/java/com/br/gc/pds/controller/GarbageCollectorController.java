package com.br.gc.pds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.gc.pds.model.Administrador;

@Controller
public class GarbageCollectorController {
	
	@Autowired
	Environment env;
	
	
	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/",method= RequestMethod.GET)
	public String loginForm(){
		return "administrador/loginForm";
	}
	
	@RequestMapping(value="/login")
	public String login(HttpSession session,@RequestParam String login,@RequestParam String senha,Model model){
		
		String loginAdmin = env.getProperty("admin.login");
		String senhaAdmin = env.getProperty("admin.senha");
		
		if(loginAdmin.equals(login) && senhaAdmin.equals(senha)){
			Administrador admin = new Administrador();
			admin.setLogin(loginAdmin);
			admin.setSenha(senhaAdmin);
			session.setAttribute("usuario_logado",admin);
			return "redirect:home";
		}
		model.addAttribute("loginError",true);
		return "administrador/loginForm";
	}
	
}