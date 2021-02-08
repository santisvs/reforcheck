package com.reforcheck.backend.elementos.inodoros.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.inodoro.Inodoro;
import com.reforcheck.backend.elementos.inodoros.services.InodoroService;

@RefreshScope
@RestController
public class InodoroController {

	private static Logger log = LoggerFactory.getLogger(InodoroController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private InodoroService inodoroService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Inodoro> listar() {
		return inodoroService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Inodoro> listarByReferencia(@RequestBody List<String> referencias) {
		return inodoroService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Inodoro> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return inodoroService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Inodoro> listarByIdEstancia(@PathVariable String idEstancia) {
		return inodoroService.findAllByIdEstancia(idEstancia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Inodoro detalle(@PathVariable Long id) {
		return inodoroService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdElem")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Inodoro buscar(@PathVariable String referencia) {
		return inodoroService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSaveAll")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Inodoro> crear(@RequestBody List<Inodoro> inodoros) {
		return inodoroService.saveAll(inodoros);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Inodoro editar(@RequestBody Inodoro inodoro, @PathVariable Long id) {
		return inodoroService.update(inodoro, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			inodoroService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la instalacion con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Inodoro> metodoReturnFindAll() {
		return new ArrayList<Inodoro>();
	}
	
	public List<Inodoro> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Inodoro>();
	}
	
	public List<Inodoro> metodoReturnFindAllByIdEstancia(String idEstancias) {
		return new ArrayList<Inodoro>();
	}
	
	public Inodoro metodoReturnFindById(Long id) {
		return null;
	}
	
	public Inodoro metodoReturnFindByIdElem(String idElem) {
		return null;
	}
	
	public List<Inodoro> metodoReturnSaveAll(List<Inodoro> inodoros) {
		return new ArrayList<Inodoro>();
	}
	
	public Inodoro metodoReturnUpdate(Inodoro inodoro, Long id) {
		return null;
	}

}
