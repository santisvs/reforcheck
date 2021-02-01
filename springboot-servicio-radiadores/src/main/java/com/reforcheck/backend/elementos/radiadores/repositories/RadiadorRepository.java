package com.reforcheck.backend.elementos.radiadores.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.radiador.Radiador;

public interface RadiadorRepository extends PagingAndSortingRepository<Radiador, Long> {

	public Radiador findByIdElem(@Param("idElem") String idElem);

	public List<Radiador> findByIdEstancia(@Param("idEstancia") String idEstancia);
}
