package com.reforcheck.backend.elementos.bidets.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.bidet.Bidet;
import com.reforcheck.backend.elementos.bidets.services.BidetService;

@RefreshScope
@RestController
public class BidetController {

	private static Logger log = LoggerFactory.getLogger(BidetController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private BidetService bidetService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Bidet> listar() {
		return bidetService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Bidet> listarByReferencia(@RequestBody List<String> referencias) {
		return bidetService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Bidet> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return bidetService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Bidet> listarByIdEstancia(@PathVariable String idEstancia) {
		return bidetService.findAllByIdEstancia(idEstancia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Bidet detalle(@PathVariable Long id) {
		return bidetService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdElem")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Bidet buscar(@PathVariable String referencia) {
		return bidetService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSaveAll")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Bidet> crear(@RequestBody List<Bidet> bidets) {
		return bidetService.saveAll(bidets);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Bidet editar(@RequestBody Bidet bidet, @PathVariable Long id) {
		return bidetService.update(bidet, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			bidetService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la instalacion con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Bidet> metodoReturnFindAll() {
		return new ArrayList<Bidet>();
	}
	
	public List<Bidet> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Bidet>();
	}
	
	public List<Bidet> metodoReturnFindAllByIdEstancia(String idEstancias) {
		return new ArrayList<Bidet>();
	}
	
	public Bidet metodoReturnFindById(Long id) {
		return null;
	}
	
	public Bidet metodoReturnFindByIdElem(String idElem) {
		return null;
	}
	
	public List<Bidet> metodoReturnSaveAll(List<Bidet> bidets) {
		return new ArrayList<Bidet>();
	}
	
	public Bidet metodoReturnUpdate(Bidet bidet, Long id) {
		return null;
	}

}
