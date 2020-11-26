package com.practi.app.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// Inyectar el bean UsuarioService
	@Autowired
	private UserDetailsService usuarioService;

	/*
	 * Registrar en el contexto de Spring el objeto BCryptPasswordEncoder. Lo
	 * utilizaremos para encritar el password
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * Registrar en el contexto de Spring el objeto AuthenticationManager. Lo
	 * inyectaremos en el servidor de autorizacion de OAuth2
	 */
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	/*
	 * Sobreescribir el método de WebSecurityConfigurerAdapter para registrar el
	 * objeto usuarioService en el AuthenticationManager
	 * 
	 * @Autowired para pasar el bean AuthenticationManagerBuilder
	 */
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}

}
