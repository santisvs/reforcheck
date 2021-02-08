package com.reforcheck.backend.elementos.armarios.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.armario.Armario;

public interface ArmarioRepository extends PagingAndSortingRepository<Armario, Long> {

	public Armario findByIdElem(@Param("idElem") String idElem);
	
	public List<Armario> findByIdEstancia(@Param("idEstancia") String idEstancia);
}
