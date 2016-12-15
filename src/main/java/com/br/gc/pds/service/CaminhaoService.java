package com.br.gc.pds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gc.pds.model.Caminhao;
import com.br.gc.pds.repository.CaminhaoRepository;

@Service
public class CaminhaoService {
	
	@Autowired
	CaminhaoRepository caminhaoRepository;
	
	public void cadastrarCaminhao(Caminhao caminhao){
		this.caminhaoRepository.save(caminhao);
	}
	
	public Caminhao buscarCaminhao(Long id) {
		return this.caminhaoRepository.findOne(id);
	}
	
	public void deletarCaminhao(Caminhao caminhao){
		this.caminhaoRepository.delete(caminhao);
	}
	
	public List<Caminhao> listarCaminhao() {
		return (List<Caminhao>) this.caminhaoRepository.findAll();
	}
}
