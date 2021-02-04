package com.reforcheck.backend.elementos.instalaciones.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.instalacion.Instalacion;
import com.reforcheck.backend.elementos.instalaciones.services.InstalacionService;

@RefreshScope
@RestController
public class InstalacionController {

	private static Logger log = LoggerFactory.getLogger(InstalacionController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private InstalacionService instalacionService;

	@HystrixCommand(fallbackMethod = "metodoReturnEmptyVacio")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Instalacion> listar() {
		return instalacionService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnEmpty")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Instalacion> listarByReferencia(@RequestBody List<String> referencias) {
		return instalacionService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnEmpty")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Instalacion> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return instalacionService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnNull")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public Instalacion listarByIdEstancia(@PathVariable String idEstancia) {
		return instalacionService.findByIdEstancia(idEstancia);
	}
	
	@HystrixCommand(fallbackMethod = "metodoReturnNullId")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Instalacion detalle(@PathVariable Long id) {
		return instalacionService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnNull")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Instalacion buscar(@PathVariable String referencia) {
		return instalacionService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnNullInstalacion")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Instalacion crear(@RequestBody Instalacion instalacion) {
		return instalacionService.save(instalacion);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnNullInstalacionId")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Instalacion editar(@RequestBody Instalacion instalacion, @PathVariable Long id) {
		return instalacionService.update(instalacion, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			instalacionService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la instalacion con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Instalacion> metodoReturnEmptyVacio() {
		return new ArrayList<Instalacion>();
	}
	
	public List<Instalacion> metodoReturnEmpty(List<String> referencias) {
		return new ArrayList<Instalacion>();
	}
	
	public Instalacion metodoReturnNullId(Long id) {
		return null;
	}
	
	public Instalacion metodoReturnNull(String idEstancia) {
		return null;
	}
	
	public Instalacion metodoReturnNullInstalacion(Instalacion instalacion) {
		return null;
	}
	
	public Instalacion metodoReturnNullInstalacionId(Instalacion instalacion, Long id) {
		return null;
	}

}
