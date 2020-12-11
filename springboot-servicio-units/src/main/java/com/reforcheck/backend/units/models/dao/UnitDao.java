package com.reforcheck.backend.units.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.reforcheck.backend.commons.entities.mysql.models.Unit;

public interface UnitDao extends PagingAndSortingRepository<Unit, Long>{

	public Unit findByName(String name);
}
