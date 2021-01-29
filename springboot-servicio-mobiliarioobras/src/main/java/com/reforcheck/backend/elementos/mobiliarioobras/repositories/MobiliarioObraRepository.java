package com.reforcheck.backend.elementos.mobiliarioobras.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.mobiliarioobra.MobiliarioObra;

public interface MobiliarioObraRepository extends PagingAndSortingRepository<MobiliarioObra, Long> {

	public MobiliarioObra findByIdElem(@Param("idElem") String idElem);
	
	public MobiliarioObra findByIdEstancia(@Param("idEstancia") String idEstancia);
}
