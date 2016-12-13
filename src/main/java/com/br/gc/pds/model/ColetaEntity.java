package com.br.gc.pds.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "coleta")
public class ColetaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coleta_id")
	private Long id;
	@OneToMany(mappedBy = "coleta", cascade = CascadeType.REMOVE)
	private List<LixeiraEntity> lixeiras = new ArrayList<LixeiraEntity>();
	@ManyToOne
	private Caminhao caminhao;

	public Caminhao getCaminhao() {
		return caminhao;
	}

	public void setCaminhao(Caminhao caminhao) {
		this.caminhao = caminhao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LixeiraEntity> getLixeiras() {
		return lixeiras;
	}

	public void setLixeiras(List<LixeiraEntity> lixeiras) {
		this.lixeiras = lixeiras;
	}

}