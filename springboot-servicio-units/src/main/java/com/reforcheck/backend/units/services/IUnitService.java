package com.reforcheck.backend.units.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.Unit;

public interface IUnitService {

	public List<Unit> findAll();

	public Unit findById(Long id);

	public Unit findByName(String name);

	public Unit save(Unit unit);

	public void deleteById(Long id);

}
