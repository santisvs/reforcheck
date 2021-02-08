package com.reforcheck.backend.elementos.solados.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.solado.Solado;

public interface SoladoRepository extends PagingAndSortingRepository<Solado, Long> {

	public Solado findByIdElem(@Param("idElem") String idElem);
	
	public Solado findByIdEstancia(@Param("idEstancia") String idEstancia);
}
