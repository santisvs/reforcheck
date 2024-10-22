package com.reforcheck.backend.oauth.services;

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
import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;
import com.reforcheck.backend.oauth.clients.UsuarioFeignClient;

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
public class OAuthService implements IOAuthService, UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(OAuthService.class);

	@Autowired
	private UsuarioFeignClient clientUsuarioFeign;

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

			Usuario usuario = clientUsuarioFeign.buscar(username);

			List<GrantedAuthority> authorities = usuario.getRoles()
					.stream()
					.map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
					.collect(Collectors.toList());

			log.info(String.format(ConstantsApp.LOG_USER_AUTHORIZED, usuario.getEmail(), authorities.toString()));

			return new User(usuario.getEmail(), usuario.getPassword(), usuario.getActivo(), true, true, true,
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
	public Usuario findByEmail(String email) {
		return clientUsuarioFeign.buscar(email);
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		return clientUsuarioFeign.editar(usuario, id);
	}

	


}
