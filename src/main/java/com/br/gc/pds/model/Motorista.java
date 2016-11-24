package com.br.gc.pds.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "motorista")
public class Motorista {

	private String carteira_motorista;
	private String nome;
	private int idade;
	//private Caminhao caminhao;
	
	public Motorista() {}

	public String getCarteira_motorista() {
		return carteira_motorista;
	}

	public void setCarteira_motorista(String carteira_motorista) {
		this.carteira_motorista = carteira_motorista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
}
