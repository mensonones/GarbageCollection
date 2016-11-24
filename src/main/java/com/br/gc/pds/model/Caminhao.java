package com.br.gc.pds.model;

import org.springframework.data.annotation.Id;

public class Caminhao {
	
	@Id
	private long id_caminhão;
	private String placa;
	private double carga_total;
}
