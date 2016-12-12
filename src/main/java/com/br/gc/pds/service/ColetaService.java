package com.br.gc.pds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.repository.ColetaRepository;

@Service
public class ColetaService {

	@Autowired
	ColetaRepository coletaRepository;
	
	public ColetaEntity salvar(ColetaEntity coleta) {
		this.coletaRepository.save(coleta);
		return coleta;
	}
	
	public ColetaEntity buscarColeta(Long id) {
		return this.coletaRepository.findOne(id);
	}
	
	public void deletarColeta(ColetaEntity coleta){
		this.coletaRepository.delete(coleta);
	}
	
	public List<ColetaEntity> listar() {
		return (List<ColetaEntity>) this.coletaRepository.findAll();
	}
	
}