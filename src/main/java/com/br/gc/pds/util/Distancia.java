package com.br.gc.pds.util;

import com.br.gc.pds.model.LixeiraEntity;

public class Distancia {
	private LixeiraEntity lixeira_origem;
	private LixeiraEntity lixeira_destino;
	private double distancia;

	public LixeiraEntity getLixeira_origem() {
		return lixeira_origem;
	}

	public void setLixeira_origem(LixeiraEntity lixeira_origem) {
		this.lixeira_origem = lixeira_origem;
	}

	public LixeiraEntity getLixeira_destino() {
		return lixeira_destino;
	}

	public void setLixeira_destino(LixeiraEntity lixeira_destino) {
		this.lixeira_destino = lixeira_destino;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

}
