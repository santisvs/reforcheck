package com.reforcheck.backend.elementos.radiadores.controllers;

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

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Radiador> listar() {
		return radiadorService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Radiador> listarByReferencia(@RequestBody List<String> referencias) {
		return radiadorService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Radiador> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return radiadorService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Radiador> listarByIdEstancia(@PathVariable String idEstancia) {
		return radiadorService.findAllByIdEstancia(idEstancia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Radiador detalle(@PathVariable Long id) {
		return radiadorService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdElem")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Radiador buscar(@PathVariable String referencia) {
		return radiadorService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSaveAll")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Radiador> crear(@RequestBody List<Radiador> radiadores) {
		return radiadorService.saveAll(radiadores);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Radiador editar(@RequestBody Radiador radiador, @PathVariable Long id) {
		return radiadorService.update(radiador, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			radiadorService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la instalacion con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Radiador> metodoReturnFindAll() {
		return new ArrayList<Radiador>();
	}
	
	public List<Radiador> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Radiador>();
	}
	
	public List<Radiador> metodoReturnFindAllByIdEstancia(String idEstancias) {
		return new ArrayList<Radiador>();
	}
	
	public Radiador metodoReturnFindById(Long id) {
		return null;
	}
	
	public Radiador metodoReturnFindByIdElem(String idElem) {
		return null;
	}
	
	public List<Radiador> metodoReturnSaveAll(List<Radiador> radiadores) {
		return new ArrayList<Radiador>();
	}
	
	public Radiador metodoReturnUpdate(Radiador radiador, Long id) {
		return null;
	}

}
