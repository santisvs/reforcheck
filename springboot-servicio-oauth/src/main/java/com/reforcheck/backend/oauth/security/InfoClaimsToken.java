package com.reforcheck.backend.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;
import com.reforcheck.backend.oauth.services.IOAuthService;

/**
 * <b>InfoClaimsToken</b> <br>
 * Clase que permite incluir información adicional al token (claims).
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
/*
 * Registramos con @Component esta clase en el contexto Spring para luego poder
 * inyectarlo en el Authorization Server
 */
@Component
public class InfoClaimsToken implements TokenEnhancer {

	/*
	 * Inyectamos el servicio usuarioService con la implementación de
	 * IUsuarioService
	 */
	@Autowired
	private IOAuthService usuarioService;

	/*
	 * Método para enriquecer el token con información del usuario
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Map<String, Object> info = new HashMap<String, Object>();
		Usuario usuario = usuarioService.findByEmail(authentication.getName());
		info.put("idUsuario", usuario.getId());
		info.put("email", usuario.getEmail());
		info.put("activo", usuario.getActivo());
		info.put("roles", usuario.getRoles());

		/*
		 * Incluimos la info al accessToken. Para ello utilizamos la implementación
		 * DefaultOAuth2AccessToken de OAuth2AccessToken para disponer del método
		 * setAditionalInformation
		 */
		System.out.println(accessToken);
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		System.out.println(accessToken);
		return accessToken;
	}

}
