package com.br.gc.pds.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.br.gc.pds.model.Lixeiras.Lixeira.StatusCapacidade;
import com.br.gc.pds.model.Lixeiras.Lixeira.StatusColeta;


@Entity
@Table(name="lixeira")
public class LixeiraEntity {

	@Id
	@Column(name="lixeira_id")
	private Long id;
	@Column(name="localizacao")
	private String localizacao;
	@Column(name="peso")
	private Float peso;
	@Column(name="status_capacidade")
	private StatusCapacidade statusCapacidade;
	@Column(name="status_coleta")
	private StatusColeta statusColeta;
	
	@ManyToMany(mappedBy="lixeiras",fetch=FetchType.LAZY)
	private List<ColetaEntity> coletas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public StatusCapacidade getStatusCapacidade() {
		return statusCapacidade;
	}
	public void setStatusCapacidade(StatusCapacidade statusCapacidade) {
		this.statusCapacidade = statusCapacidade;
	}
	public StatusColeta getStatusColeta() {
		return statusColeta;
	}
	public void setStatusColeta(StatusColeta statusColeta) {
		this.statusColeta = statusColeta;
	}
	public List<ColetaEntity> getColeta() {
		return coletas;
	}
	public void setColeta(List<ColetaEntity> coletas) {
		this.coletas = coletas;
	}
	
	
}