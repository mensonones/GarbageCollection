package com.br.gc.pds.entities;

public class Caminhao {
	private String placa;
	private double capacidadeTotal;
	private boolean estaEmRota;
	private boolean estaCheio;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getCapacidadeTotal() {
		return capacidadeTotal;
	}

	public void setCapacidadeTotal(double capacidadeTotal) {
		this.capacidadeTotal = capacidadeTotal;
	}

	public boolean isEstaEmRota() {
		return estaEmRota;
	}

	public void setEstaEmRota(boolean estaEmRota) {
		this.estaEmRota = estaEmRota;
	}

	public boolean isEstaCheio() {
		return estaCheio;
	}

	public void setEstaCheio(boolean estaCheio) {
		this.estaCheio = estaCheio;
	}

}
