package com.reforcheck.backend.elementos.instalaciones.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.instalacion.Instalacion;

public interface InstalacionRepository extends PagingAndSortingRepository<Instalacion, Long> {

	public Instalacion findByIdElem(@Param("idElem") String idElem);
	
	public Instalacion findByIdEstancia(@Param("idEstancia") String idEstancia);
}
