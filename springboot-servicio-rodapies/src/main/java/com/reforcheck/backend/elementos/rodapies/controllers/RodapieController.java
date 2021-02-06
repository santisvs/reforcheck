package com.reforcheck.backend.elementos.rodapies.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.rodapie.Rodapie;
import com.reforcheck.backend.elementos.rodapies.services.RodapieService;

@RefreshScope
@RestController
public class RodapieController {

	private static Logger log = LoggerFactory.getLogger(RodapieController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private RodapieService rodapieService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Rodapie> listar() {
		return rodapieService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Rodapie> listarByReferencia(@RequestBody List<String> referencias) {
		return rodapieService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Rodapie> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return rodapieService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public Rodapie listarByIdEstancia(@PathVariable String idEstancia) {
		return rodapieService.findByIdEstancia(idEstancia);
	}
	
	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Rodapie detalle(@PathVariable Long id) {
		return rodapieService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Rodapie buscar(@PathVariable String referencia) {
		return rodapieService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSave")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Rodapie crear(@RequestBody Rodapie rodapie) {
		return rodapieService.save(rodapie);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Rodapie editar(@RequestBody Rodapie rodapie, @PathVariable Long id) {
		return rodapieService.update(rodapie, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			rodapieService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la rodapie con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Rodapie> metodoReturnFindAll() {
		return new ArrayList<Rodapie>();
	}
	
	public List<Rodapie> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Rodapie>();
	}
	
	public Rodapie metodoReturnFindById(Long id) {
		return null;
	}
	
	public Rodapie metodoReturnFindByIdEstancia(String idEstancia) {
		return null;
	}
	
	public Rodapie metodoReturnSave(Rodapie rodapie) {
		return null;
	}
	
	public Rodapie metodoReturnUpdate(Rodapie rodapie, Long id) {
		return null;
	}

}
