package com.br.gc.pds.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Rota {
	
	@Id
	private Long id_rota;
	private String inicio;
	private String destino;
	private List<Lixeira> listaLixeira = new ArrayList<Lixeira>();
	
	public void mostrarLixeiras(){
		
	}

	public Long getId_rota() {
		return id_rota;
	}

	public void setId_rota(Long id_rota) {
		this.id_rota = id_rota;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public List<Lixeira> getListaLixeira() {
		return listaLixeira;
	}

	public void setListaLixeira(List<Lixeira> listaLixeira) {
		this.listaLixeira = listaLixeira;
	}
	
	
	
}
