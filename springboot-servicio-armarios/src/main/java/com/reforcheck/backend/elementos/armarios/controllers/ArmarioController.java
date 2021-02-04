package com.reforcheck.backend.elementos.armarios.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.armario.Armario;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.instalacion.Instalacion;
import com.reforcheck.backend.elementos.armarios.services.ArmarioService;

@RefreshScope
@RestController
public class ArmarioController {

	private static Logger log = LoggerFactory.getLogger(ArmarioController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private ArmarioService armarioService;

	@HystrixCommand(fallbackMethod = "metodoReturnEmpty")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Armario> listar() {
		return armarioService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnEmpty")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Armario> listarByReferencia(@RequestBody List<String> referencias) {
		return armarioService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnEmpty")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Armario> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return armarioService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnEmpty")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Armario> listarByIdEstancia(@PathVariable String idEstancia) {
		return armarioService.findAllByIdEstancia(idEstancia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnNull")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Armario detalle(@PathVariable Long id) {
		return armarioService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnNull")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Armario buscar(@PathVariable String referencia) {
		return armarioService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnEmpty")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Armario> crear(@RequestBody List<Armario> armarios) {
		return armarioService.saveAll(armarios);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnNull")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Armario editar(@RequestBody Armario armario, @PathVariable Long id) {
		return armarioService.update(armario, id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnNull")
	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		armarioService.delete(id);
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Armario> metodoReturnEmpty(List<String> referencias) {
		return new ArrayList<Armario>();
	}
	
	public Instalacion metodoReturnNull(String idEstancia) {
		return null;
	}

}
