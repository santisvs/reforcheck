package com.reforcheck.backend.elementos.lavabos.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo.Lavabo;

public interface LavaboRepository extends PagingAndSortingRepository<Lavabo, Long> {

	public Lavabo findByIdElem(@Param("idElem") String idElem);

	public List<Lavabo> findByIdEstancia(@Param("idEstancia") String idEstancia);
}
