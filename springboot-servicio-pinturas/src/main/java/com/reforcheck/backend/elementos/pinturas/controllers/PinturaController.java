package com.reforcheck.backend.elementos.pinturas.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.pintura.Pintura;
import com.reforcheck.backend.elementos.pinturas.services.PinturaService;

@RefreshScope
@RestController
public class PinturaController {

	private static Logger log = LoggerFactory.getLogger(PinturaController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private PinturaService pinturaService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Pintura> listar() {
		return pinturaService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Pintura> listarByReferencia(@RequestBody List<String> referencias) {
		return pinturaService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Pintura> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return pinturaService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public Pintura listarByIdEstancia(@PathVariable String idEstancia) {
		return pinturaService.findByIdEstancia(idEstancia);
	}
	
	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Pintura detalle(@PathVariable Long id) {
		return pinturaService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Pintura buscar(@PathVariable String referencia) {
		return pinturaService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSave")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Pintura crear(@RequestBody Pintura pintura) {
		return pinturaService.save(pintura);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Pintura editar(@RequestBody Pintura pintura, @PathVariable Long id) {
		return pinturaService.update(pintura, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			pinturaService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la pintura con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Pintura> metodoReturnFindAll() {
		return new ArrayList<Pintura>();
	}
	
	public List<Pintura> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Pintura>();
	}
	
	public Pintura metodoReturnFindById(Long id) {
		return null;
	}
	
	public Pintura metodoReturnFindByIdEstancia(String idEstancia) {
		return null;
	}
	
	public Pintura metodoReturnSave(Pintura pintura) {
		return null;
	}
	
	public Pintura metodoReturnUpdate(Pintura pintura, Long id) {
		return null;
	}

}
