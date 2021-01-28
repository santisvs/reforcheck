package com.reforcheck.backend.elementos.climatizaciones.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.climatizacion.Climatizacion;

public interface ClimatizacionRepository extends PagingAndSortingRepository<Climatizacion, Long> {

	public Climatizacion findByIdElem(@Param("idElem") String idElem);

	public List<Climatizacion> findByIdEstancia(@Param("idEstancia") String idEstancia);
}
