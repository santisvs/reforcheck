package com.reforcheck.backend.elementos.iluminaciones.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.iluminacion.Iluminacion;

public interface IluminacionRepository extends PagingAndSortingRepository<Iluminacion, Long> {

	public Iluminacion findByIdElem(@Param("idElem") String idElem);
	
	public Iluminacion findByIdEstancia(@Param("idEstancia") String idEstancia);
}
