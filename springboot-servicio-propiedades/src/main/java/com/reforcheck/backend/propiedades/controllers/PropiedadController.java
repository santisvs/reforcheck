package com.reforcheck.backend.propiedades.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.propiedad.Propiedad;
import com.reforcheck.backend.propiedades.services.PropiedadService;

@RefreshScope
@RestController
public class PropiedadController {

	private static Logger log = LoggerFactory.getLogger(PropiedadController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private PropiedadService propiedadService;

	
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Propiedad> listar() {
		return propiedadService.findAll();
	}
	
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Propiedad> listarByReferencia(@RequestBody List<String> referencias) {
		return propiedadService.findAllByIdPropiedad(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Propiedad> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return propiedadService.findAllByIdPropiedad(referencias);
	}
	
	@GetMapping(ConstantsApp.URI_WITH_PROPIETARIO_REQUEST_PARAM)
	public List<Propiedad> listarByIdPropietario(@PathVariable String idPropietario) {
		return propiedadService.findAllByIdPropietario(idPropietario);
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Propiedad detalle(@PathVariable Long id) {
		return propiedadService.findById(id);
	}

	public Propiedad metodoAlternativo(Long id) {
		Propiedad propiedad = new Propiedad();
		Producto producto = new Producto();

		producto.setId(id);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);

		return propiedad;

	}
	
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Propiedad buscar(@PathVariable String referencia) {
		return propiedadService.findByIdPropiedad(referencia);
	}

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Propiedad> crear(@RequestBody List<Propiedad> propiedades) {
		return propiedadService.saveAll(propiedades);
	}

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Propiedad editar(@RequestBody Propiedad propiedad, @PathVariable Long id) {
		return propiedadService.update(propiedad, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		propiedadService.delete(id);
	}
}
