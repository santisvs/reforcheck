package com.reforcheck.backend.elementos.bidets.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.reforcheck.backend.commons.entities.mysql.models.estancia.Bidet;

@RepositoryRestResource(path = "repo")
public interface BidetRepository extends PagingAndSortingRepository<Bidet, Long> {

	@RestResource(path = "buscar")
	public Bidet findByIdElem(@Param("idElem") String idElem);
}
