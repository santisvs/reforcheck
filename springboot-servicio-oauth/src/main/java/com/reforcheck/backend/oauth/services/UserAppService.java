package com.reforcheck.backend.oauth.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.postgresql.models.UserApp;
import com.reforcheck.backend.oauth.clients.UserAppFeignClient;

import brave.Tracer;
import feign.FeignException;

/**
 * <b>UsuarioService</b> <br>
 * Servicio para autenticar al usuario. Implementa la clase propia de Spring
 * Security UserDetailsService. Tambien implementa la interfaz IUsuarioService
 * para poder disponer de la implementacion de los métodos de IUsuarioService.
 * 
 * Utilizamos el cliente Feign UsuarioFeignClient para conectar con el
 * microservicio Usuarios.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Service
public class UserAppService implements IUserAppService, UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(UserAppService.class);

	@Autowired
	private UserAppFeignClient client;

	@Autowired
	private Tracer tracer;

	/*
	 * Implementación de la interfaz UserDetailsService
	 * 
	 * Método para crear el usuario Spring Security.
	 * 
	 * Incluye los granted authorities. Son los roles de un usuario.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {

			UserApp usuario = client.findByUsername(username);

			List<GrantedAuthority> authorities = usuario.getListRole()
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.getName()))
					.collect(Collectors.toList());

			log.info(String.format(ConstantsApp.LOG_USER_AUTHORIZED, usuario.getUsername(), authorities.toString()));

			return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
					authorities);
		} catch (FeignException e) {
			tracer.currentSpan().tag(ConstantsApp.ZIPKIN_ERROR_MSG,
					String.format(ConstantsApp.EXCEPTION_USER_NO_EXIST, username) + ConstantsTypes.CHAR_DOS_PUNTOS + e.getMessage());
			throw new UsernameNotFoundException(String.format(ConstantsApp.EXCEPTION_USER_NO_EXIST, username));
		}
	}

	/*
	 * Implementación de la interfaz IUsuarioService
	 * 
	 * Método para obtener el usuario de BBDD
	 */
	@Override
	public UserApp findByUsername(String username) {
		return client.findByUsername(username);
	}

	@Override
	public UserApp update(UserApp usuario, Long id) {
		return client.update(usuario, id);
	}

}
