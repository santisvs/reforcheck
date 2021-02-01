package com.reforcheck.backend.elementos.radiadores.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.radiador.Radiador;
import com.reforcheck.backend.elementos.radiadores.services.RadiadorService;

@RefreshScope
@RestController
public class RadiadorController {

	private static Logger log = LoggerFactory.getLogger(RadiadorController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private RadiadorService radiadorService;

	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Radiador> listar() {
		return radiadorService.findAll();
	}

	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Radiador> listarByReferencia(@RequestBody List<String> referencias) {
		return radiadorService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Radiador> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return radiadorService.findAllByIdElem(referencias);
	}

	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Radiador> listarByIdEstancia(@PathVariable String idEstancia) {
		return radiadorService.findAllByIdEstancia(idEstancia);
	}

	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Radiador detalle(@PathVariable Long id) {
		return radiadorService.findById(id);
	}

	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Radiador buscar(@PathVariable String referencia) {
		return radiadorService.findByIdElem(referencia);
	}

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Radiador> crear(@RequestBody List<Radiador> radiadores) {
		return radiadorService.saveAll(radiadores);
	}

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Radiador editar(@RequestBody Radiador radiador, @PathVariable Long id) {
		return radiadorService.update(radiador, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		radiadorService.delete(id);
	}

}
