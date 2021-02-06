package com.reforcheck.backend.elementos.revestimientos.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.revestimiento.Revestimiento;
import com.reforcheck.backend.elementos.revestimientos.services.RevestimientoService;

@RefreshScope
@RestController
public class RevestimientoController {

	private static Logger log = LoggerFactory.getLogger(RevestimientoController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private RevestimientoService revestimientoService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Revestimiento> listar() {
		return revestimientoService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Revestimiento> listarByReferencia(@RequestBody List<String> referencias) {
		return revestimientoService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Revestimiento> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return revestimientoService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public Revestimiento listarByIdEstancia(@PathVariable String idEstancia) {
		return revestimientoService.findByIdEstancia(idEstancia);
	}
	
	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Revestimiento detalle(@PathVariable Long id) {
		return revestimientoService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Revestimiento buscar(@PathVariable String referencia) {
		return revestimientoService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSave")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Revestimiento crear(@RequestBody Revestimiento revestimiento) {
		return revestimientoService.save(revestimiento);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Revestimiento editar(@RequestBody Revestimiento revestimiento, @PathVariable Long id) {
		return revestimientoService.update(revestimiento, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			revestimientoService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la revestimiento con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Revestimiento> metodoReturnFindAll() {
		return new ArrayList<Revestimiento>();
	}
	
	public List<Revestimiento> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Revestimiento>();
	}
	
	public Revestimiento metodoReturnFindById(Long id) {
		return null;
	}
	
	public Revestimiento metodoReturnFindByIdEstancia(String idEstancia) {
		return null;
	}
	
	public Revestimiento metodoReturnSave(Revestimiento revestimiento) {
		return null;
	}
	
	public Revestimiento metodoReturnUpdate(Revestimiento revestimiento, Long id) {
		return null;
	}

}
