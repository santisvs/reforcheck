package com.reforcheck.backend.elementos.armarios.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.reforcheck.backend.commons.entities.mysql.models.estancia.Armario;

@RepositoryRestResource(path = "repo")
public interface ArmarioRepository extends PagingAndSortingRepository<Armario, Long> {

	@RestResource(path = "buscar")
	public Armario findByIdElem(@Param("idElem") String idElem);
}
