package com.reforcheck.backend.elementos.inodoros.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.inodoro.Inodoro;

public interface InodoroRepository extends PagingAndSortingRepository<Inodoro, Long> {

	public Inodoro findByIdElem(@Param("idElem") String idElem);

	public List<Inodoro> findByIdEstancia(@Param("idEstancia") String idEstancia);
}
