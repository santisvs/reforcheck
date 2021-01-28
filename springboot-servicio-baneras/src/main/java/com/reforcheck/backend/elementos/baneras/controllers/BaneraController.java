package com.reforcheck.backend.elementos.baneras.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.banera.Banera;
import com.reforcheck.backend.elementos.baneras.services.BaneraService;

@RefreshScope
@RestController
public class BaneraController {

	private static Logger log = LoggerFactory.getLogger(BaneraController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private BaneraService baneraService;

	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Banera> listar() {
		return baneraService.findAll();
	}

	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Banera> listarByReferencia(@RequestBody List<String> referencias) {
		return baneraService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Banera> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return baneraService.findAllByIdElem(referencias);
	}

	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Banera> listarByIdEstancia(@PathVariable String idEstancia) {
		return baneraService.findAllByIdEstancia(idEstancia);
	}

	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Banera detalle(@PathVariable Long id) {
		return baneraService.findById(id);
	}

	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Banera buscar(@PathVariable String referencia) {
		return baneraService.findByIdElem(referencia);
	}

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Banera> crear(@RequestBody List<Banera> baneras) {
		return baneraService.saveAll(baneras);
	}

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Banera editar(@RequestBody Banera banera, @PathVariable Long id) {
		return baneraService.update(banera, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		baneraService.delete(id);
	}

}
