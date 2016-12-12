package com.br.gc.pds.model;

import java.util.Date;

public class Relatorio {
	private Date data;
	private String rua;
	private String placa;
	private double qtdLixo;
	private double quilometros;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getQtdLixo() {
		return qtdLixo;
	}

	public void setQtdLixo(double qtdLixo) {
		this.qtdLixo = qtdLixo;
	}

	public double getQuilometros() {
		return quilometros;
	}

	public void setQuilometros(double quilometros) {
		this.quilometros = quilometros;
	}

}
