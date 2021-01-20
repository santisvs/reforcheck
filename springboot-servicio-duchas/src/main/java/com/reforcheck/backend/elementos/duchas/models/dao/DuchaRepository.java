package com.reforcheck.backend.elementos.duchas.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.reforcheck.backend.commons.entities.mysql.models.estancia.Ducha;

@RepositoryRestResource(path = "repo")
public interface DuchaRepository extends PagingAndSortingRepository<Ducha, Long> {

	@RestResource(path = "buscar")
	public Ducha findByIdElem(@Param("idElem") String idElem);
}
