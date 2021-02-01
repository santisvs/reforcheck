package com.reforcheck.backend.elementos.revestimientos.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

@FeignClient(name = ConstantsApp.FABRICANTES_SERVICE_NAME)
public interface FabricanteClientRest {
	
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Fabricante> listar();
	
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Fabricante> listarByReferencia(@RequestBody List<String> referencias);

	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Fabricante detalle(@PathVariable Long id);

	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Fabricante buscar(@PathVariable String referencia);

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Fabricante> crear(@RequestBody List<Fabricante> fabricantes);

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Fabricante editar(@RequestBody Fabricante fabricante, @PathVariable Long id);

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public void eliminar(@PathVariable Long id);

}
