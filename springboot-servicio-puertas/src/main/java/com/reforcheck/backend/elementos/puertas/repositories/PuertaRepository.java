package com.reforcheck.backend.elementos.puertas.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.puerta.Puerta;

public interface PuertaRepository extends PagingAndSortingRepository<Puerta, Long> {

	public Puerta findByIdElem(@Param("idElem") String idElem);

	public List<Puerta> findByIdEstancia(@Param("idEstancia") String idEstancia);
}
