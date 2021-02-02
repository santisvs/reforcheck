package com.reforcheck.backend.estancias.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.estancia.Estancia;

public interface EstanciaRepository extends PagingAndSortingRepository<Estancia, Long> {

	public Estancia findByIdEstancia(@Param("idEstancia") String idEstancia);
	
	public List<Estancia> findByIdPlanta(@Param("idPlanta") String idPlanta);
	
	public List<Estancia> findByIdPropiedad(@Param("idPropiedad") String idPropiedad);

}
