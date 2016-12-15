package com.br.gc.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.br.gc.pds.model.Administrador;

@Component
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		
		Administrador logado = (Administrador)request.getSession().getAttribute("usuario_logado");
		if(uri.endsWith("/") || uri.endsWith("/login")){
			return true;
		}
		
		if(logado != null){
			return true;
		}
		
		response.sendRedirect("/");
		return false;
	}
}
