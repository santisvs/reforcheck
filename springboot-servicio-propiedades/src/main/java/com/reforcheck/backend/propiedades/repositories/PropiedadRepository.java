package com.reforcheck.backend.propiedades.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.propiedad.Propiedad;

public interface PropiedadRepository extends PagingAndSortingRepository<Propiedad, Long> {

	public Propiedad findByIdPropiedad(@Param("idPropiedad") String idPropiedad);
	
	public List<Propiedad> findByIdPropietario(@Param("idPropietario") String idPropietario);

}
