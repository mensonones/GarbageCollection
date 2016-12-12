package com.br.gc.pds.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.gc.pds.model.ColetaEntity;

@Repository
public interface ColetaRepository extends CrudRepository<ColetaEntity, Long>{

}
