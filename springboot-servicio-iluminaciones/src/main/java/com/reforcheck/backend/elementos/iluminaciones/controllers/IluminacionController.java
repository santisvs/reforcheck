package com.reforcheck.backend.elementos.iluminaciones.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.iluminacion.Iluminacion;
import com.reforcheck.backend.elementos.iluminaciones.services.IluminacionService;

@RefreshScope
@RestController
public class IluminacionController {

	private static Logger log = LoggerFactory.getLogger(IluminacionController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private IluminacionService iluminacionService;

	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Iluminacion> listar() {
		return iluminacionService.findAll();
	}

	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Iluminacion> listarByReferencia(@RequestBody List<String> referencias) {
		return iluminacionService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Iluminacion> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return iluminacionService.findAllByIdElem(referencias);
	}

	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public Iluminacion listarByIdEstancia(@PathVariable String idEstancia) {
		return iluminacionService.findByIdEstancia(idEstancia);
	}

	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Iluminacion detalle(@PathVariable Long id) {
		return iluminacionService.findById(id);
	}

	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Iluminacion buscar(@PathVariable String referencia) {
		return iluminacionService.findByIdElem(referencia);
	}

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Iluminacion crear(@RequestBody Iluminacion iluminacion) {
		return iluminacionService.save(iluminacion);
	}

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Iluminacion editar(@RequestBody Iluminacion iluminacion, @PathVariable Long id) {
		return iluminacionService.update(iluminacion, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		iluminacionService.delete(id);
	}

}
