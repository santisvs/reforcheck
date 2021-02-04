package com.reforcheck.backend.estancias.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo.Lavabo;

@FeignClient(name = ConstantsApp.LAVABOS_SERVICE_NAME)
public interface LavaboClientRest {
	
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Lavabo> listar();
	
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Lavabo> listarByReferencia(@RequestBody List<String> referencias);
	
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Lavabo> listarByIdEstancia(@PathVariable String idEstancia);
	
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Lavabo detalle(@PathVariable Long id);
	
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Lavabo buscar(@PathVariable String referencia);
	
	@Async
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Lavabo> crear(@RequestBody List<Lavabo> lavabos);
	
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Lavabo editar(@RequestBody Lavabo lavabo, @PathVariable Long id);

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public void eliminar(@PathVariable Long id);
}
