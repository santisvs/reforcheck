package com.reforcheck.backend.elementos.ventanas.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.ventana.Ventana;
import com.reforcheck.backend.elementos.ventanas.services.VentanaService;

@RefreshScope
@RestController
public class VentanaController {

	private static Logger log = LoggerFactory.getLogger(VentanaController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private VentanaService ventanaService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Ventana> listar() {
		return ventanaService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Ventana> listarByReferencia(@RequestBody List<String> referencias) {
		return ventanaService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Ventana> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return ventanaService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Ventana> listarByIdEstancia(@PathVariable String idEstancia) {
		return ventanaService.findAllByIdEstancia(idEstancia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Ventana detalle(@PathVariable Long id) {
		return ventanaService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdElem")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Ventana buscar(@PathVariable String referencia) {
		return ventanaService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSaveAll")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Ventana> crear(@RequestBody List<Ventana> ventanas) {
		return ventanaService.saveAll(ventanas);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Ventana editar(@RequestBody Ventana ventana, @PathVariable Long id) {
		return ventanaService.update(ventana, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			ventanaService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la instalacion con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Ventana> metodoReturnFindAll() {
		return new ArrayList<Ventana>();
	}
	
	public List<Ventana> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Ventana>();
	}
	
	public List<Ventana> metodoReturnFindAllByIdEstancia(String idEstancias) {
		return new ArrayList<Ventana>();
	}
	
	public Ventana metodoReturnFindById(Long id) {
		return null;
	}
	
	public Ventana metodoReturnFindByIdElem(String idElem) {
		return null;
	}
	
	public List<Ventana> metodoReturnSaveAll(List<Ventana> ventanas) {
		return new ArrayList<Ventana>();
	}
	
	public Ventana metodoReturnUpdate(Ventana ventana, Long id) {
		return null;
	}

}
