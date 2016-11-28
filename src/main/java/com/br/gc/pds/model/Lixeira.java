package com.br.gc.pds.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

public class Lixeira {
	
	@Id
	private int id_lixeira;
	private long latitude;
	private long longitude;
	private BigDecimal capacidade;
	private boolean isCheia;
	
	public void enviarNotificacao(){
		
	}
	
	public int getId_lixeira() {
		return id_lixeira;
	}


	public void setId_lixeira(int id_lixeira) {
		this.id_lixeira = id_lixeira;
	}


	public BigDecimal getCapacidade() {
		return capacidade;
	}


	public void setCapacidade(BigDecimal capacidade) {
		this.capacidade = capacidade;
	}


	public boolean isCheia() {
		return isCheia;
	}


	public void setCheia(boolean isCheia) {
		this.isCheia = isCheia;
	}


	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

}
