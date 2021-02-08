package com.reforcheck.backend.elementos.techos.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.techo.Techo;

public interface TechoRepository extends PagingAndSortingRepository<Techo, Long> {

	public Techo findByIdElem(@Param("idElem") String idElem);
	
	public Techo findByIdEstancia(@Param("idEstancia") String idEstancia);
}
