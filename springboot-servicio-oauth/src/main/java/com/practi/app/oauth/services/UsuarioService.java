package com.practi.app.oauth.services;

import java.util.ArrayList;
import java.util.List;

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

import com.practi.app.commons.constants.ConstantsApp;
import com.practi.app.commons.models.entity.Usuario;
import com.practi.app.oauth.clients.UsuarioFeignClient;

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
public class UsuarioService implements IUsuarioService, UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioFeignClient client;

	/*
	 * Implementación de la interfaz UserDetailsService
	 * 
	 * Método para crear el usuario Spring Security.
	 * 
	 * Incluye los granted authorities. Son los roles de un usuario.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = client.findByUsername(username);
		if (usuario == null) {
			log.info(String.format(ConstantsApp.EXCEPTION_USER_NO_EXIST, username));
			throw new UsernameNotFoundException(String.format(ConstantsApp.EXCEPTION_USER_NO_EXIST, username));
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRol().getName());
		authorities.add(authority);

		log.info(String.format(ConstantsApp.LOG_USER_AUTHORIZED, usuario.getUsername(), authority.getAuthority()));

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	/*
	 * Implementación de la interfaz IUsuarioService
	 * 
	 * Método para obtener el usuario de BBDD
	 */
	@Override
	public Usuario findByUsername(String username) {
		return client.findByUsername(username);
	}

}
