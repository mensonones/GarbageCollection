package com.br.gc.pds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gc.pds.model.LixeiraEntity;
import com.br.gc.pds.repository.LixeiraRepository;

@Service
public class LixeiraService {

	@Autowired
	LixeiraRepository lixeiraRepository;
	
	public void salvar(LixeiraEntity lixeira){
		this.lixeiraRepository.save(lixeira);
	}
	
} 