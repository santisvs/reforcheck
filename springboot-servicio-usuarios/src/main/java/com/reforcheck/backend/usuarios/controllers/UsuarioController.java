package com.reforcheck.backend.usuarios.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;
import com.reforcheck.backend.usuarios.services.UsuarioService;

@RefreshScope
@RestController
public class UsuarioController {

	private static Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private UsuarioService usuarioService;

	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Usuario> listar() {
		return usuarioService.findAll();
	}

	@GetMapping(ConstantsApp.URI_WITH_EMAIL_REQUEST_PARAM)
	public Usuario buscar(@PathVariable String email) {
		return usuarioService.findByEmail(email);
	}

	/*
	 * Método duplicado findByEmail Se ha creado este método para que pueda
	 * responder a las peticion de los cliente Feign. Feign modifica la petición de
	 * GET a POST cuando se hace una request con información en el body
	 */
	@PostMapping(ConstantsApp.URI_WITH_EMAIL_REQUEST_PARAM)
	public Usuario buscarFeign(@PathVariable String email) {
		return usuarioService.findByEmail(email);
	}

	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Usuario detalle(@PathVariable Long id) {
		return usuarioService.findById(id);
	}

	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Usuario> crear(@RequestBody List<Usuario> usuarios) {
		return usuarioService.saveAll(usuarios);
	}

	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario editar(@RequestBody Usuario usuario, @PathVariable Long id) {
		return usuarioService.update(usuario, id);
	}

	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		usuarioService.delete(id);
	}

}
