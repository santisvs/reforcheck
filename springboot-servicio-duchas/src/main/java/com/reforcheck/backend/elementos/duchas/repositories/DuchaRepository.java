package com.reforcheck.backend.elementos.duchas.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.ducha.Ducha;


public interface DuchaRepository extends PagingAndSortingRepository<Ducha, Long> {

	public Ducha findByIdElem(@Param("idElem") String idElem);
	
	public List<Ducha> findByIdEstancia(@Param("idEstancia") String idEstancia);
}