package com.practi.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.practi.app.commons.constants.ConstantsApp;
import com.practi.app.commons.constants.ConstantsTypes;
import com.practi.app.commons.models.entity.Usuario;
import com.practi.app.oauth.services.IUsuarioService;

import feign.FeignException;

/**
 * <b>AuthenticationSucessErrorHandler</b> <br>
 * Clase evento para manejar los flujos de Sucess y Error en la autenticación
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@Component
public class AuthenticationSucessErrorHandler implements AuthenticationEventPublisher {

	private static Logger log = LoggerFactory.getLogger(AuthenticationSucessErrorHandler.class);
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		log.info(String.format(ConstantsApp.LOG_SUCESS_LOGIN, user.getUsername()));
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		
		if (usuario.getLoginAttempts() != null && usuario.getLoginAttempts() >= ConstantsTypes.ENT_0) {
			usuario.setLoginAttempts(ConstantsTypes.ENT_0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		
		try {
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if (usuario.getLoginAttempts() == null) {
				usuario.setLoginAttempts(ConstantsTypes.ENT_0);
			}
			
			usuario.setLoginAttempts(usuario.getLoginAttempts()+ConstantsTypes.ENT_1);
			log.info(String.format(ConstantsApp.LOG_USER_ATTEMPS_LOGIN, usuario.getUsername(), usuario.getLoginAttempts()));
			
			if(usuario.getLoginAttempts() >= ConstantsTypes.ENT_3) {
				log.info(String.format(ConstantsApp.LOG_USER_UNABLED, usuario.getUsername()));
				usuario.setEnabled(false);
			}
			
			usuarioService.update(usuario, usuario.getId());
			
			
		} catch (FeignException e) {
			log.error(String.format(ConstantsApp.EXCEPTION_USER_NO_EXIST, authentication.getName()));
		} finally {
			log.error(String.format(ConstantsApp.EXCEPTION_ERROR_LOGIN, exception.getMessage()));
		}
	}

}
