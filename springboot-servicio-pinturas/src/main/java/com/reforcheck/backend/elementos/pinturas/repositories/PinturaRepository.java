package com.reforcheck.backend.elementos.pinturas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.pintura.Pintura;

public interface PinturaRepository extends PagingAndSortingRepository<Pintura, Long> {

	public Pintura findByIdElem(@Param("idElem") String idElem);
	
	public Pintura findByIdEstancia(@Param("idEstancia") String idEstancia);
}
