package com.reforcheck.backend.elementos.puertas.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.puerta.Puerta;
import com.reforcheck.backend.elementos.puertas.services.PuertaService;

@RefreshScope
@RestController
public class PuertaController {

	private static Logger log = LoggerFactory.getLogger(PuertaController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private PuertaService puertaService;

	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Puerta> listar() {
		return puertaService.findAll();
	}

	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Puerta> listarByReferencia(@RequestBody List<String> referencias) {
		return puertaService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Puerta> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return puertaService.findAllByIdElem(referencias);
	}

	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Puerta> listarByIdEstancia(@PathVariable String idEstancia) {
		return puertaService.findAllByIdEstancia(idEstancia);
	}

	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Puerta detalle(@PathVariable Long id) {
		return puertaService.findById(id);
	}

	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Puerta buscar(@PathVariable String referencia) {
		return puertaService.findByIdElem(referencia);
	}

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Puerta> crear(@RequestBody List<Puerta> puertas) {
		return puertaService.saveAll(puertas);
	}

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Puerta editar(@RequestBody Puerta puerta, @PathVariable Long id) {
		return puertaService.update(puerta, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		puertaService.delete(id);
	}

}
