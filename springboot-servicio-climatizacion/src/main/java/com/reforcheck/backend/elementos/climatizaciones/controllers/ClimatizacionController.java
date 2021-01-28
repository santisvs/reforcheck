package com.reforcheck.backend.elementos.climatizaciones.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.climatizacion.Climatizacion;
import com.reforcheck.backend.elementos.climatizaciones.services.ClimatizacionService;

@RefreshScope
@RestController
public class ClimatizacionController {

	private static Logger log = LoggerFactory.getLogger(ClimatizacionController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private ClimatizacionService climatizacionService;

	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Climatizacion> listar() {
		return climatizacionService.findAll();
	}

	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Climatizacion> listarByReferencia(@RequestBody List<String> referencias) {
		return climatizacionService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Climatizacion> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return climatizacionService.findAllByIdElem(referencias);
	}

	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Climatizacion> listarByIdEstancia(@PathVariable String idEstancia) {
		return climatizacionService.findAllByIdEstancia(idEstancia);
	}

	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Climatizacion detalle(@PathVariable Long id) {
		return climatizacionService.findById(id);
	}

	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Climatizacion buscar(@PathVariable String referencia) {
		return climatizacionService.findByIdElem(referencia);
	}

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Climatizacion> crear(@RequestBody List<Climatizacion> climatizaciones) {
		return climatizacionService.saveAll(climatizaciones);
	}

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Climatizacion editar(@RequestBody Climatizacion climatizacion, @PathVariable Long id) {
		return climatizacionService.update(climatizacion, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		climatizacionService.delete(id);
	}

}
