package com.reforcheck.backend.elementos.duchas.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.ducha.Ducha;
import com.reforcheck.backend.elementos.duchas.services.DuchaService;

@RefreshScope
@RestController
public class DuchaController {

	private static Logger log = LoggerFactory.getLogger(DuchaController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private DuchaService duchaService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Ducha> listar() {
		return duchaService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Ducha> listarByReferencia(@RequestBody List<String> referencias) {
		return duchaService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Ducha> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return duchaService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Ducha> listarByIdEstancia(@PathVariable String idEstancia) {
		return duchaService.findAllByIdEstancia(idEstancia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Ducha detalle(@PathVariable Long id) {
		return duchaService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdElem")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Ducha buscar(@PathVariable String referencia) {
		return duchaService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSaveAll")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Ducha> crear(@RequestBody List<Ducha> duchas) {
		return duchaService.saveAll(duchas);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Ducha editar(@RequestBody Ducha ducha, @PathVariable Long id) {
		return duchaService.update(ducha, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			duchaService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la instalacion con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Ducha> metodoReturnFindAll() {
		return new ArrayList<Ducha>();
	}
	
	public List<Ducha> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Ducha>();
	}
	
	public List<Ducha> metodoReturnFindAllByIdEstancia(String idEstancias) {
		return new ArrayList<Ducha>();
	}
	
	public Ducha metodoReturnFindById(Long id) {
		return null;
	}
	
	public Ducha metodoReturnFindByIdElem(String idElem) {
		return null;
	}
	
	public List<Ducha> metodoReturnSaveAll(List<Ducha> duchas) {
		return new ArrayList<Ducha>();
	}
	
	public Ducha metodoReturnUpdate(Ducha ducha, Long id) {
		return null;
	}

}
