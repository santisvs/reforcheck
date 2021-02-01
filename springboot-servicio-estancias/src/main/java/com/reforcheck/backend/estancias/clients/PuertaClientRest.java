package com.reforcheck.backend.estancias.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.puerta.Puerta;

@FeignClient(name = ConstantsApp.PUERTAS_SERVICE_NAME)
public interface PuertaClientRest {
	
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Puerta> listar();
	
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Puerta> listarByReferencia(@RequestBody List<String> referencias);
	
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Puerta> listarByIdEstancia(@PathVariable String idEstancia);
	
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Puerta detalle(@PathVariable Long id);
	
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Puerta buscar(@PathVariable String referencia);
	
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Puerta> crear(@RequestBody List<Puerta> puertas);
	
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Puerta editar(@RequestBody Puerta puerta, @PathVariable Long id);

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public void eliminar(@PathVariable Long id);
}
