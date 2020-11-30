package com.practi.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.practi.app.commons.constants.ConstantsApp;

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

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		log.info(String.format(ConstantsApp.LOG_SUCESS_LOGIN, user.getUsername()));
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		log.error(String.format(ConstantsApp.EXCEPTION_ERROR_LOGIN, user.getUsername(), exception.getMessage()));
	}

}
