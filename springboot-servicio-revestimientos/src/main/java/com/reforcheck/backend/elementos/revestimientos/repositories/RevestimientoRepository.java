package com.reforcheck.backend.elementos.revestimientos.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.revestimiento.Revestimiento;

public interface RevestimientoRepository extends PagingAndSortingRepository<Revestimiento, Long> {

	public Revestimiento findByIdElem(@Param("idElem") String idElem);
	
	public Revestimiento findByIdEstancia(@Param("idEstancia") String idEstancia);
}
