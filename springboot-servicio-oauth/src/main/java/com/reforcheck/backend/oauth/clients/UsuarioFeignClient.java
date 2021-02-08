package com.reforcheck.backend.oauth.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;


/**
 * <b>UsuarioFeignClient</b> <br>
 * Cliente Feign para conectar con el microservicio usuarios. Al utilizar Feign,
 * usamos el balanceo de carga adecuado.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@FeignClient(name = ConstantsApp.USER_SERVICE_NAME)
public interface UsuarioFeignClient {

	@GetMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Usuario> listar();
	
	@GetMapping(ConstantsApp.URI_WITH_EMAIL_REQUEST_PARAM)
	public Usuario buscar(@PathVariable String email);
	
	@GetMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Usuario detalle(@PathVariable Long id);
	
	@PostMapping(ConstantsApp.URI_WITHOUT_REQUEST_PARAM)
	public List<Usuario> crear(@RequestBody List<Usuario> usuarios);
	
	@PutMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public Usuario editar(@RequestBody Usuario usuario, @PathVariable Long id);
	
	@DeleteMapping(ConstantsApp.URI_WITH_ID_REQUEST_PARAM)
	public void eliminar(@PathVariable Long id);

}
