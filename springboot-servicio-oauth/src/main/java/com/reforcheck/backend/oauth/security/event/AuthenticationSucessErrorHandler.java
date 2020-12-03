package com.reforcheck.backend.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.postgresql.models.Usuario;
import com.reforcheck.backend.oauth.services.IUsuarioService;

import brave.Tracer;
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

	// Inyectar los beans de spring
	@Autowired
	private Environment env;

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String frontendClient = env.getProperty(ConstantsApp.PROPERTY_FRONT_CLIENT_DEFAULT_NAME);

		if (frontendClient.equals(user.getUsername())) {
			log.info(String.format(ConstantsApp.LOG_TRY_LOGIN, user.getUsername()));
		} else {

			log.info(String.format(ConstantsApp.LOG_SUCESS_LOGIN, user.getUsername()));

			Usuario usuario = usuarioService.findByUsername(authentication.getName());

			if (usuario.getLoginAttempts() != null && usuario.getLoginAttempts() >= ConstantsTypes.ENT_0) {
				usuario.setLoginAttempts(ConstantsTypes.ENT_0);
				usuarioService.update(usuario, usuario.getId());
			}

		}

	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

		String frontendClient = env.getProperty(ConstantsApp.PROPERTY_FRONT_CLIENT_DEFAULT_NAME);
		
		StringBuilder errors = new StringBuilder();
		errors.append(ConstantsApp.LOG_FAILURE_LOGIN);
		try {
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if (usuario.getLoginAttempts() == null) {
				usuario.setLoginAttempts(ConstantsTypes.ENT_0);
			}

			usuario.setLoginAttempts(usuario.getLoginAttempts() + ConstantsTypes.ENT_1);
			log.info(String.format(ConstantsApp.LOG_USER_ATTEMPS_LOGIN, usuario.getUsername(),
					usuario.getLoginAttempts()));
			
			errors.append(ConstantsTypes.TRACE_CONCAT + String.format(ConstantsApp.LOG_USER_ATTEMPS_LOGIN, usuario.getUsername(),
					usuario.getLoginAttempts()));

			if (usuario.getLoginAttempts() >= ConstantsTypes.ENT_3) {
				log.info(String.format(ConstantsApp.LOG_USER_UNABLED, usuario.getUsername()));
				errors.append(ConstantsTypes.TRACE_CONCAT + String.format(ConstantsApp.LOG_USER_UNABLED, usuario.getUsername()));
				usuario.setEnabled(false);
			}

			usuarioService.update(usuario, usuario.getId());
			
			tracer.currentSpan().tag(ConstantsApp.ZIPKIN_ERROR_MSG, errors.toString());

		} catch (FeignException e) {
			if (frontendClient.equals(authentication.getName())) {
				log.info(String.format(ConstantsApp.LOG_TRY_LOGIN, authentication.getName()));
			} else {
				log.error(String.format(ConstantsApp.EXCEPTION_USER_NO_EXIST, authentication.getName()));
			}
		} finally {
			log.error(String.format(ConstantsApp.EXCEPTION_ERROR_LOGIN, exception.getMessage()));
		}
	}

}
