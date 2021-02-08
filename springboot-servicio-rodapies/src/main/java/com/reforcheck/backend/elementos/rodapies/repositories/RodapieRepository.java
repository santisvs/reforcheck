package com.reforcheck.backend.elementos.rodapies.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.rodapie.Rodapie;

public interface RodapieRepository extends PagingAndSortingRepository<Rodapie, Long> {

	public Rodapie findByIdElem(@Param("idElem") String idElem);
	
	public Rodapie findByIdEstancia(@Param("idEstancia") String idEstancia);
}
