package com.reforcheck.backend.units.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.Unit;
import com.reforcheck.backend.units.models.dao.UnitDao;

@Service
public class UnitServiceImpl implements IUnitService {

	@Autowired
	private UnitDao unitDao;

	@Override
	@Transactional(readOnly = true)
	public List<Unit> findAll() {
		return (List<Unit>) unitDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Unit findById(Long id) {
		return unitDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Unit findByName(String name) {
		return unitDao.findByName(name);
	}

	@Override
	public List<Unit> findByNameContaining(String name) {
		return unitDao.findByNameContaining(name);
	}

	@Override
	@Transactional
	public Unit save(Unit unit) {
		return unitDao.save(unit);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		unitDao.deleteById(id);
	}

}
