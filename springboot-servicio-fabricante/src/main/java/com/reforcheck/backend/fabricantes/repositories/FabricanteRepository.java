package com.reforcheck.backend.fabricantes.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

public interface FabricanteRepository extends PagingAndSortingRepository<Fabricante, Long> {

	public Fabricante findByReferencia(String referencia);
	
	public void deleteByReferencia(String referencia);

}
