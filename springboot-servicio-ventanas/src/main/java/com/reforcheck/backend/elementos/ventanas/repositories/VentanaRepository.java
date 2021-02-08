package com.reforcheck.backend.elementos.ventanas.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.ventana.Ventana;

public interface VentanaRepository extends PagingAndSortingRepository<Ventana, Long> {

	public Ventana findByIdElem(@Param("idElem") String idElem);

	public List<Ventana> findByIdEstancia(@Param("idEstancia") String idEstancia);
}
