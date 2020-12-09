package com.reforcheck.backend.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.entities.postgresql.models.UserApp;


/**
 * <b>UsuarioFeignClient</b> <br>
 * Cliente Feign para conectar con el microservicio usuarios. Al utilizar Feign,
 * usamos el balanceo de carga adecuado.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@FeignClient(name = ConstantsApp.USER_SERVICE_NAME)
public interface UserAppFeignClient {

	@GetMapping(ConstantsApp.URI_USER_SEARCH)
	public UserApp findByUsername(@RequestParam String username);
	
	@PutMapping(ConstantsApp.URI_USER_UPDATE)
	public UserApp update(@RequestBody UserApp usuario, @PathVariable Long id);

}
