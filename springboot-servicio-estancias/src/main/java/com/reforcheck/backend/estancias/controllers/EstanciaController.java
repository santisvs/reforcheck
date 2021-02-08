package com.reforcheck.backend.estancias.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.Producto;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Estancia;
import com.reforcheck.backend.estancias.services.EstanciaService;

@RefreshScope
@RestController
public class EstanciaController {

	private static Logger log = LoggerFactory.getLogger(EstanciaController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private EstanciaService estanciaService;

	
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Estancia> listar() {
		return estanciaService.findAll();
	}
	
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Estancia> listarByReferencia(@RequestBody List<String> referencias) {
		return estanciaService.findAllByIdEstancia(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Estancia> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return estanciaService.findAllByIdEstancia(referencias);
	}
	
	
	@GetMapping(ConstantsApp.URI_WITH_PLANTA_REQUEST_PARAM)
	public List<Estancia> listarByIdPlanta(@PathVariable String idPlanta) {
		return estanciaService.findAllByIdPlanta(idPlanta);
	}
	
	@GetMapping(ConstantsApp.URI_WITH_PROPIEDAD_REQUEST_PARAM)
	public List<Estancia> listarByIdPropiedad(@PathVariable String idPropiedad) {
		return estanciaService.findAllByIdPropiedad(idPropiedad);
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Estancia detalle(@PathVariable Long id) {
		return estanciaService.findById(id);
	}

	public Estancia metodoAlternativo(Long id) {
		Estancia estancia = new Estancia();
		Producto producto = new Producto();

		producto.setId(id);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);

		return estancia;

	}
	
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Estancia buscar(@PathVariable String referencia) {
		return estanciaService.findByIdEstancia(referencia);
	}

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Estancia> crear(@RequestBody List<Estancia> estancias) {
		return estanciaService.saveAll(estancias);
	}

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Estancia editar(@RequestBody Estancia estancia, @PathVariable Long id) {
		return estanciaService.update(estancia, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		estanciaService.delete(id);
	}
}
