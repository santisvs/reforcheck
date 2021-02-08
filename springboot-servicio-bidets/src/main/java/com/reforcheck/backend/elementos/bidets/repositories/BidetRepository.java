package com.reforcheck.backend.elementos.bidets.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.bidet.Bidet;

public interface BidetRepository extends PagingAndSortingRepository<Bidet, Long> {

	public Bidet findByIdElem(@Param("idElem") String idElem);

	public List<Bidet> findByIdEstancia(@Param("idEstancia") String idEstancia);
}
