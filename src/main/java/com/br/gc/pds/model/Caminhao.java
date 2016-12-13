package com.br.gc.pds.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.br.gc.pds.util.StatusCaminhaoCapacidade;
import com.br.gc.pds.util.StatusCaminhaoColeta;

@Entity
@Table(name = "caminhao")
public class Caminhao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "caminhao_id")
	private Long id_caminhao;
	private String placa;
	private double capacidadeTotal;
	private StatusCaminhaoCapacidade statusCaminhaoCapacidade;
	private StatusCaminhaoColeta statusCaminhaColeta;
	
	@OneToMany(mappedBy="caminhao",cascade= CascadeType.REMOVE)
	private List<ColetaEntity> coletas;

	public Long getId_caminhao() {
		return id_caminhao;
	}

	public void setId_caminhao(Long id_caminhao) {
		this.id_caminhao = id_caminhao;
	}

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

	public StatusCaminhaoCapacidade getStatusCaminhaoCapacidade() {
		return statusCaminhaoCapacidade;
	}

	public void setStatusCaminhaoCapacidade(StatusCaminhaoCapacidade statusCaminhaoCapacidade) {
		this.statusCaminhaoCapacidade = statusCaminhaoCapacidade;
	}

	public StatusCaminhaoColeta getStatusCaminhaColeta() {
		return statusCaminhaColeta;
	}

	public void setStatusCaminhaColeta(StatusCaminhaoColeta statusCaminhaColeta) {
		this.statusCaminhaColeta = statusCaminhaColeta;
	}

	public List<ColetaEntity> getColetas() {
		return coletas;
	}

	public void setColetas(List<ColetaEntity> coletas) {
		this.coletas = coletas;
	}
}
