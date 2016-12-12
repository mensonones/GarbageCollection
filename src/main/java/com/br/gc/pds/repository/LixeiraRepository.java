package com.br.gc.pds.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.gc.pds.model.LixeiraEntity;

@Repository
public interface LixeiraRepository extends CrudRepository<LixeiraEntity, Long> {

}
