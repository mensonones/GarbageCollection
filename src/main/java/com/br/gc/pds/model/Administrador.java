package com.br.gc.pds.model;

import org.springframework.data.annotation.Id;

public class Administrador {
	
	@Id
	private long id_adm;
	private String login;
    private String senha;
		
}
