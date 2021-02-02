package com.reforcheck.backend.plantas.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.planta.Planta;
import com.reforcheck.backend.plantas.services.PlantaService;

@RefreshScope
@RestController
public class PlantaController {

	private static Logger log = LoggerFactory.getLogger(PlantaController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private PlantaService plantaService;

	
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Planta> listar() {
		return plantaService.findAll();
	}
	
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Planta> listarByReferencia(@RequestBody List<String> referencias) {
		return plantaService.findAllByIdPlanta(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Planta> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return plantaService.findAllByIdPlanta(referencias);
	}
	
	@GetMapping(ConstantsApp.URI_WITH_PROPIEDAD_REQUEST_PARAM)
	public List<Planta> listarByIdPropiedad(@PathVariable String idPropiedad) {
		return plantaService.findAllByIdPropiedad(idPropiedad);
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Planta detalle(@PathVariable Long id) {
		return plantaService.findById(id);
	}

	public Planta metodoAlternativo(Long id) {
		Planta planta = new Planta();
		Producto producto = new Producto();

		producto.setId(id);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);

		return planta;

	}
	
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Planta buscar(@PathVariable String referencia) {
		return plantaService.findByIdPlanta(referencia);
	}

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Planta> crear(@RequestBody List<Planta> plantas) {
		return plantaService.saveAll(plantas);
	}

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Planta editar(@RequestBody Planta planta, @PathVariable Long id) {
		return plantaService.update(planta, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		plantaService.delete(id);
	}
}
