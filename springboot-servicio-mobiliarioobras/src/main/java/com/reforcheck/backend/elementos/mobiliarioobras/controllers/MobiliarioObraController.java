package com.reforcheck.backend.elementos.mobiliarioobras.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.mobiliarioobra.MobiliarioObra;
import com.reforcheck.backend.elementos.mobiliarioobras.services.MobiliarioObraService;

@RefreshScope
@RestController
public class MobiliarioObraController {

	private static Logger log = LoggerFactory.getLogger(MobiliarioObraController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private MobiliarioObraService mobiliarioObraService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<MobiliarioObra> listar() {
		return mobiliarioObraService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<MobiliarioObra> listarByReferencia(@RequestBody List<String> referencias) {
		return mobiliarioObraService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<MobiliarioObra> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return mobiliarioObraService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public MobiliarioObra listarByIdEstancia(@PathVariable String idEstancia) {
		return mobiliarioObraService.findByIdEstancia(idEstancia);
	}
	
	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public MobiliarioObra detalle(@PathVariable Long id) {
		return mobiliarioObraService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public MobiliarioObra buscar(@PathVariable String referencia) {
		return mobiliarioObraService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSave")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public MobiliarioObra crear(@RequestBody MobiliarioObra mobiliarioObra) {
		return mobiliarioObraService.save(mobiliarioObra);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public MobiliarioObra editar(@RequestBody MobiliarioObra mobiliarioObra, @PathVariable Long id) {
		return mobiliarioObraService.update(mobiliarioObra, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			mobiliarioObraService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la mobiliarioObra con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<MobiliarioObra> metodoReturnFindAll() {
		return new ArrayList<MobiliarioObra>();
	}
	
	public List<MobiliarioObra> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<MobiliarioObra>();
	}
	
	public MobiliarioObra metodoReturnFindById(Long id) {
		return null;
	}
	
	public MobiliarioObra metodoReturnFindByIdEstancia(String idEstancia) {
		return null;
	}
	
	public MobiliarioObra metodoReturnSave(MobiliarioObra mobiliarioObra) {
		return null;
	}
	
	public MobiliarioObra metodoReturnUpdate(MobiliarioObra mobiliarioObra, Long id) {
		return null;
	}

}
