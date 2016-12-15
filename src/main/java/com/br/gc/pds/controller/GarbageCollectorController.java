package com.br.gc.pds.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.gc.pds.model.Administrador;

@Controller
public class GarbageCollectorController {
	
	@Autowired
	Environment env;
	
	
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
	@RequestMapping(value="/Coleta-{id}.PDF")
	public void visualizarRota(@PathVariable Long id,HttpServletResponse response) throws IOException{
		String path = "C:/Users/Carlos/git/GarbageCollection/src/main/resources/relatorios/coletas/Coleta-"+id+".PDF";
		Path file = Paths.get(path);
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename="+path);
		Files.copy(file,response.getOutputStream());
		response.getOutputStream().flush();
	}
	
	@RequestMapping(value="/verRelatorio" , method=RequestMethod.GET)
	public void verRelatorio(HttpServletResponse response) throws IOException{
		String path = "C:/Users/Carlos/git/GarbageCollection/src/main/resources/relatorios/coletas/RelatorioColeta.PDF";
		Path file = Paths.get(path);
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=RelatorioColeta");
		Files.copy(file,response.getOutputStream());
		response.getOutputStream().flush();
	}
}