package com.reforcheck.backend.elementos.solados.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.solado.Solado;
import com.reforcheck.backend.elementos.solados.services.SoladoService;

@RefreshScope
@RestController
public class SoladoController {

	private static Logger log = LoggerFactory.getLogger(SoladoController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private SoladoService soladoService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Solado> listar() {
		return soladoService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Solado> listarByReferencia(@RequestBody List<String> referencias) {
		return soladoService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Solado> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return soladoService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public Solado listarByIdEstancia(@PathVariable String idEstancia) {
		return soladoService.findByIdEstancia(idEstancia);
	}
	
	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Solado detalle(@PathVariable Long id) {
		return soladoService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Solado buscar(@PathVariable String referencia) {
		return soladoService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSave")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Solado crear(@RequestBody Solado solado) {
		return soladoService.save(solado);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Solado editar(@RequestBody Solado solado, @PathVariable Long id) {
		return soladoService.update(solado, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			soladoService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la solado con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Solado> metodoReturnFindAll() {
		return new ArrayList<Solado>();
	}
	
	public List<Solado> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Solado>();
	}
	
	public Solado metodoReturnFindById(Long id) {
		return null;
	}
	
	public Solado metodoReturnFindByIdEstancia(String idEstancia) {
		return null;
	}
	
	public Solado metodoReturnSave(Solado solado) {
		return null;
	}
	
	public Solado metodoReturnUpdate(Solado solado, Long id) {
		return null;
	}

}
