package com.br.gc.pds.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.gc.pds.model.Caminhao;
import com.br.gc.pds.model.Motorista;

public interface MotoristaRepository extends CrudRepository<Motorista,Long>{

}
