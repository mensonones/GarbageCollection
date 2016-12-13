package com.br.gc.pds.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RotasController {
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "login";
	}*/

	/*@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";
	}*/

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login-error";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "logout";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/relatorio", method = RequestMethod.GET)
	public void geraRelatorio(HttpServletResponse response) throws IOException {
		File file = new File("C:/pdf/EXTERNO.pdf");
		InputStream is = new FileInputStream(file);

		// MIME type of the file
		response.setContentType("application/octet-stream");
		// Response header
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		// Read from the file and write into the response
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastrarCaminhao() {
		return "cadastroCaminhao";
	}

	
	@RequestMapping(value = "/rotas", method = RequestMethod.GET)
	public String rotas() {
		return "rotas";
	}


}
