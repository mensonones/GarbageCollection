package com.br.gc.pds.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.br.gc.pds.util.ValorStatusColeta;

@Entity
@Table(name = "coleta")
public class ColetaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coleta_id")
	private Long idColeta;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="COLETA_LIXEIRA",joinColumns=@JoinColumn(name="coleta_id",referencedColumnName="coleta_id"),inverseJoinColumns=@JoinColumn(name="lixeira_id",referencedColumnName="lixeira_id"))
	private List<LixeiraEntity> lixeiras = new ArrayList<LixeiraEntity>();
	@ManyToOne
	private Caminhao caminhao;
	private ValorStatusColeta statusColeta;
	private String dataColeta;

	public ValorStatusColeta getStatusColeta() {
		return statusColeta;
	}

	public void setStatusColeta(ValorStatusColeta statusColeta) {
		this.statusColeta = statusColeta;
	}

	public Caminhao getCaminhao() {
		return caminhao;
	}

	public void setCaminhao(Caminhao caminhao) {
		this.caminhao = caminhao;
	}

	public Long getIdColeta() {
		return idColeta;
	}

	public void setIdColeta(Long idColeta) {
		this.idColeta = idColeta;
	}

	public List<LixeiraEntity> getLixeiras() {
		return lixeiras;
	}

	public void setLixeiras(List<LixeiraEntity> lixeiras) {
		this.lixeiras = lixeiras;
	}

	public String getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(String dataColeta) {
		this.dataColeta = dataColeta;
	}

}