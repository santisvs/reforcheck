package com.reforcheck.backend.elementos.baneras.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.banera.Banera;

public interface BaneraRepository extends PagingAndSortingRepository<Banera, Long> {

	public Banera findByIdElem(@Param("idElem") String idElem);

	public List<Banera> findByIdEstancia(@Param("idEstancia") String idEstancia);
}
