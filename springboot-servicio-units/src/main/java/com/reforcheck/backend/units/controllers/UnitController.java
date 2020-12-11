package com.reforcheck.backend.units.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.mysql.models.Unit;
import com.reforcheck.backend.units.services.IUnitService;

@RestController
public class UnitController {

	private static Logger log = LoggerFactory.getLogger(UnitController.class);

	@Autowired
	private IUnitService unitService;

	@GetMapping(ConstantsApp.URI_REST_LIST)
	public List<Unit> listAll() {
		log.info(ConstantsApp.LOG_PRODUCTO_LISTAR);
		return unitService.findAll();
	}

	@GetMapping(ConstantsApp.URI_REST_DETAIL)
	public Unit detail(@PathVariable Long id) {
		log.info(ConstantsApp.LOG_PRODUCTO_VER + id);
		return unitService.findById(id);
	}

	@GetMapping(ConstantsApp.URI_REST_FIND)
	public Unit find(@PathVariable String name) {
		log.info(ConstantsApp.LOG_PRODUCTO_VER + name);
		return unitService.findByName(name);
	}

	@PostMapping(ConstantsApp.URI_REST_CREATE)
	@ResponseStatus(HttpStatus.CREATED)
	public Unit create(@RequestBody Unit unit) {
		return unitService.save(unit);
	}

	@PutMapping(ConstantsApp.URI_REST_EDIT)
	@ResponseStatus(HttpStatus.CREATED)
	public Unit edit(@RequestBody Unit unit, @PathVariable Long id) {
		Unit unitDb = unitService.findById(id);
		unitDb.setUnit(unit);
		return unitService.save(unitDb);
	}

	@DeleteMapping(ConstantsApp.URI_REST_DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		unitService.deleteById(id);
	}
}
