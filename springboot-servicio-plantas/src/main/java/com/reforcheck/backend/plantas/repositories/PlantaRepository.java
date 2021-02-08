package com.reforcheck.backend.plantas.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.planta.Planta;

public interface PlantaRepository extends PagingAndSortingRepository<Planta, Long> {

	public Planta findByIdPlanta(@Param("idPlanta") String idPlanta);
	
	public List<Planta> findByIdPropiedad(@Param("idPropiedad") String idPropiedad);

}
