package com.br.gc.pds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Autowired
	AutorizadorInterceptor interceptador;
	
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(interceptador);
	}
}
