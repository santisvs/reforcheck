package com.practi.app.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.practi.app.commons.models.entity.Usuario;
import com.practi.app.oauth.services.IUsuarioService;

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
	private IUsuarioService usuarioService;

	/*
	 * Método para enriquecer el token con información del usuario
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Map<String, Object> info = new HashMap<String, Object>();
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		info.put("nombre", usuario.getName());
		info.put("apellido", usuario.getLastname());
		info.put("email", usuario.getEmail());

		/*
		 * Incluimos la info al accessToken. Para ello utilizamos la implementación
		 * DefaultOAuth2AccessToken de OAuth2AccessToken para disponer del método
		 * setAditionalInformation
		 */
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
