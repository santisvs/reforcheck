package com.reforcheck.backend.elementos.baneras.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.reforcheck.backend.commons.entities.mysql.models.estancia.Banera;

@RepositoryRestResource(path = "repo")
public interface BaneraRepository extends PagingAndSortingRepository<Banera, Long> {

	@RestResource(path = "buscar")
	public Banera findByIdElem(@Param("idElem") String idElem);

}
