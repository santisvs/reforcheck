package com.practi.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practi.app.commons.constants.ConstantsApp;
import com.practi.app.commons.models.entity.Usuario;

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

	@GetMapping(ConstantsApp.URI_VER)
	public Usuario findByUsername(@RequestParam String username);

}
