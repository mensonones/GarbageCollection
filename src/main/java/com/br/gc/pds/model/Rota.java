package com.br.gc.pds.model;

import org.springframework.data.annotation.Id;

public class Rota {
	
	@Id
	private Long id_rota;
	private String inicio;
	private String destino;
	
	
}
