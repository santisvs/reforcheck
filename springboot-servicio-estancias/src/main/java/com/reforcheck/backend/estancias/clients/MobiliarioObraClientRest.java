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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.mobiliarioobra.MobiliarioObra;

@FeignClient(name = ConstantsApp.MOBILIARIO_OBRAS_SERVICE_NAME)
public interface MobiliarioObraClientRest {
	
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<MobiliarioObra> listar();
	
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<MobiliarioObra> listarByReferencia(@RequestBody List<String> referencias);
	
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public MobiliarioObra listarByIdEstancia(@PathVariable String idEstancia);
	
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public MobiliarioObra detalle(@PathVariable Long id);
	
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public MobiliarioObra buscar(@PathVariable String referencia);
	
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public MobiliarioObra crear(@RequestBody MobiliarioObra mobiliarioObra);
	
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public MobiliarioObra editar(@RequestBody MobiliarioObra mobiliarioObra, @PathVariable Long id);

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public void eliminar(@PathVariable Long id);
}
