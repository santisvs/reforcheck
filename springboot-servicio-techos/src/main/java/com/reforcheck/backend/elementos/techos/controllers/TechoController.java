package com.reforcheck.backend.elementos.techos.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.techo.Techo;
import com.reforcheck.backend.elementos.techos.services.TechoService;

@RefreshScope
@RestController
public class TechoController {

	private static Logger log = LoggerFactory.getLogger(TechoController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private TechoService techoService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Techo> listar() {
		return techoService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Techo> listarByReferencia(@RequestBody List<String> referencias) {
		return techoService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Techo> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return techoService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public Techo listarByIdEstancia(@PathVariable String idEstancia) {
		return techoService.findByIdEstancia(idEstancia);
	}
	
	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Techo detalle(@PathVariable Long id) {
		return techoService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Techo buscar(@PathVariable String referencia) {
		return techoService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSave")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Techo crear(@RequestBody Techo techo) {
		return techoService.save(techo);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Techo editar(@RequestBody Techo techo, @PathVariable Long id) {
		return techoService.update(techo, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			techoService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la techo con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Techo> metodoReturnFindAll() {
		return new ArrayList<Techo>();
	}
	
	public List<Techo> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Techo>();
	}
	
	public Techo metodoReturnFindById(Long id) {
		return null;
	}
	
	public Techo metodoReturnFindByIdEstancia(String idEstancia) {
		return null;
	}
	
	public Techo metodoReturnSave(Techo techo) {
		return null;
	}
	
	public Techo metodoReturnUpdate(Techo techo, Long id) {
		return null;
	}

}
