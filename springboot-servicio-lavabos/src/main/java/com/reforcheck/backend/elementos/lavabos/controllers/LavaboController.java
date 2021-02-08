package com.reforcheck.backend.elementos.lavabos.controllers;

import java.util.ArrayList;
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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo.Lavabo;
import com.reforcheck.backend.elementos.lavabos.services.LavaboService;

@RefreshScope
@RestController
public class LavaboController {

	private static Logger log = LoggerFactory.getLogger(LavaboController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private LavaboService lavaboService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Lavabo> listar() {
		return lavaboService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Lavabo> listarByReferencia(@RequestBody List<String> referencias) {
		return lavaboService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Lavabo> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return lavaboService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Lavabo> listarByIdEstancia(@PathVariable String idEstancia) {
		return lavaboService.findAllByIdEstancia(idEstancia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Lavabo detalle(@PathVariable Long id) {
		return lavaboService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdElem")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Lavabo buscar(@PathVariable String referencia) {
		return lavaboService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSaveAll")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Lavabo> crear(@RequestBody List<Lavabo> lavabos) {
		return lavaboService.saveAll(lavabos);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Lavabo editar(@RequestBody Lavabo lavabo, @PathVariable Long id) {
		return lavaboService.update(lavabo, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			lavaboService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la instalacion con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Lavabo> metodoReturnFindAll() {
		return new ArrayList<Lavabo>();
	}
	
	public List<Lavabo> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Lavabo>();
	}
	
	public List<Lavabo> metodoReturnFindAllByIdEstancia(String idEstancias) {
		return new ArrayList<Lavabo>();
	}
	
	public Lavabo metodoReturnFindById(Long id) {
		return null;
	}
	
	public Lavabo metodoReturnFindByIdElem(String idElem) {
		return null;
	}
	
	public List<Lavabo> metodoReturnSaveAll(List<Lavabo> lavabos) {
		return new ArrayList<Lavabo>();
	}
	
	public Lavabo metodoReturnUpdate(Lavabo lavabo, Long id) {
		return null;
	}

}
