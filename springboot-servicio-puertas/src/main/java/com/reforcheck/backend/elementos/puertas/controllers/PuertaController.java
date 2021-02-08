package com.reforcheck.backend.elementos.puertas.controllers;

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

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Puerta> listar() {
		return puertaService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Puerta> listarByReferencia(@RequestBody List<String> referencias) {
		return puertaService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Puerta> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return puertaService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Puerta> listarByIdEstancia(@PathVariable String idEstancia) {
		return puertaService.findAllByIdEstancia(idEstancia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Puerta detalle(@PathVariable Long id) {
		return puertaService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdElem")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Puerta buscar(@PathVariable String referencia) {
		return puertaService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSaveAll")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Puerta> crear(@RequestBody List<Puerta> puertas) {
		return puertaService.saveAll(puertas);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Puerta editar(@RequestBody Puerta puerta, @PathVariable Long id) {
		return puertaService.update(puerta, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			puertaService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la instalacion con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Puerta> metodoReturnFindAll() {
		return new ArrayList<Puerta>();
	}
	
	public List<Puerta> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Puerta>();
	}
	
	public List<Puerta> metodoReturnFindAllByIdEstancia(String idEstancias) {
		return new ArrayList<Puerta>();
	}
	
	public Puerta metodoReturnFindById(Long id) {
		return null;
	}
	
	public Puerta metodoReturnFindByIdElem(String idElem) {
		return null;
	}
	
	public List<Puerta> metodoReturnSaveAll(List<Puerta> puertas) {
		return new ArrayList<Puerta>();
	}
	
	public Puerta metodoReturnUpdate(Puerta puerta, Long id) {
		return null;
	}

}
