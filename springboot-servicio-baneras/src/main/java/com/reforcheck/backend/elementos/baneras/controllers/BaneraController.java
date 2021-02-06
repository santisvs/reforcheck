package com.reforcheck.backend.elementos.baneras.controllers;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.banera.Banera;
import com.reforcheck.backend.elementos.baneras.services.BaneraService;

@RefreshScope
@RestController
public class BaneraController {

	private static Logger log = LoggerFactory.getLogger(BaneraController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private BaneraService baneraService;

	@HystrixCommand(fallbackMethod = "metodoReturnFindAll")
	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Banera> listar() {
		return baneraService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@GetMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Banera> listarByReferencia(@RequestBody List<String> referencias) {
		return baneraService.findAllByIdElem(referencias);
	}

	/*
	 * Método duplicado listarByReferencia Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdElem")
	@PostMapping(ConstantsApp.URI_REFERENCIAS_WITHOUT_REQUEST_PARAM)
	public List<Banera> listarByReferenciaFeign(@RequestBody List<String> referencias) {
		return baneraService.findAllByIdElem(referencias);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindAllByIdEstancia")
	@GetMapping(ConstantsApp.URI_WITH_ESTANCIA_REQUEST_PARAM)
	public List<Banera> listarByIdEstancia(@PathVariable String idEstancia) {
		return baneraService.findAllByIdEstancia(idEstancia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindById")
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Banera detalle(@PathVariable Long id) {
		return baneraService.findById(id);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnFindByIdElem")
	@GetMapping(ConstantsApp.URI_WITH_REFERENCIA_REQUEST_PARAM)
	public Banera buscar(@PathVariable String referencia) {
		return baneraService.findByIdElem(referencia);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnSaveAll")
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Banera> crear(@RequestBody List<Banera> baneras) {
		return baneraService.saveAll(baneras);
	}

	@HystrixCommand(fallbackMethod = "metodoReturnUpdate")
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Banera editar(@RequestBody Banera banera, @PathVariable Long id) {
		return baneraService.update(banera, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean eliminar(@PathVariable Long id) {
		boolean res = false;
		try {
			baneraService.delete(id);
			res = true;
		} catch (Exception e) {
			log.error("No se ha podido eliminar la instalacion con id="+id);
		}
		return res;
	}
	
	/*
	 * Métodos alternativos Hystrix
	 */
	public List<Banera> metodoReturnFindAll() {
		return new ArrayList<Banera>();
	}
	
	public List<Banera> metodoReturnFindAllByIdElem(List<String> referencias) {
		return new ArrayList<Banera>();
	}
	
	public List<Banera> metodoReturnFindAllByIdEstancia(String idEstancias) {
		return new ArrayList<Banera>();
	}
	
	public Banera metodoReturnFindById(Long id) {
		return null;
	}
	
	public Banera metodoReturnFindByIdElem(String idElem) {
		return null;
	}
	
	public List<Banera> metodoReturnSaveAll(List<Banera> baneras) {
		return new ArrayList<Banera>();
	}
	
	public Banera metodoReturnUpdate(Banera banera, Long id) {
		return null;
	}

}
