package com.reforcheck.backend.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.mysql.models.Producto;


@FeignClient(name = ConstantsApp.PRODUCT_SERVICE_NAME)
public interface ProductoClienteRest {

	@GetMapping(ConstantsApp.URI_LISTAR)
	public List<Producto> listar();

	@GetMapping(ConstantsApp.URI_VER)
	public Producto detalle(@PathVariable Long id);

	@PostMapping(ConstantsApp.URI_CREAR)
	public Producto crear(@RequestBody Producto producto);
	
	@PutMapping(ConstantsApp.URI_EDITAR)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id);
	
	@DeleteMapping(ConstantsApp.URI_ELIMINAR)
	public void eliminar(@PathVariable Long id);
}
