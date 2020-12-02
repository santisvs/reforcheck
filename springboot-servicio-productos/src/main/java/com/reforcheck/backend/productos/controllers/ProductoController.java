package com.reforcheck.backend.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.mysql.models.Producto;
import com.reforcheck.backend.productos.models.service.IProductoService;

@RestController
public class ProductoController {

	private static Logger log = LoggerFactory.getLogger(ProductoController.class);

	// @Autowired
	// private Environment env;

	@Value(ConstantsApp.PROPERTY_PORT)
	private Integer port;

	@Autowired
	private IProductoService productoService;

	@GetMapping(ConstantsApp.URI_LISTAR)
	public List<Producto> listar() {
		log.info(ConstantsApp.LOG_PRODUCTO_LISTAR);
		return productoService.findAll().stream().map(producto -> {
			// producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping(ConstantsApp.URI_VER)
	public Producto detalle(@PathVariable Long id) {
		log.info(ConstantsApp.LOG_PRODUCTO_VER + id);
		Producto producto = productoService.findById(id);
		// producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(port);

		/*
		 * try { Thread.sleep(2000L); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		return producto;
	}

	@PostMapping(ConstantsApp.URI_CREAR)
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping(ConstantsApp.URI_EDITAR)
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDb = productoService.findById(id);
		
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		
		return productoService.save(productoDb);
	}
	
	@DeleteMapping(ConstantsApp.URI_ELIMINAR)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}
}
