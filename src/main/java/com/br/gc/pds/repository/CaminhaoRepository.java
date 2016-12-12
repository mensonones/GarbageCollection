package com.br.gc.pds.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.gc.pds.model.Caminhao;

@Repository
public interface CaminhaoRepository extends CrudRepository<Caminhao, Long> {

}
