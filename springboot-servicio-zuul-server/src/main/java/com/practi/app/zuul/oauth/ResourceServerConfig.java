package com.practi.app.zuul.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.practi.app.commons.constants.ConstantsApp;

/**
 * <b>ResourceServerConfig</b> <br>
 * Clase de configuración del servidor de recursos <b>Servidor enrutador
 * ZUUL></b> para el backend Reforcheck. Configuramos el servidor ZUUL para que
 * incluya la seguridad de todos los accesos a las rutas y dar el acceso a los
 * clientes segun el token y el rol que tengan. Tambein es el encargadoo de
 * validar que el token sea el correccto con la misma firma con la que se genera
 * el token en el servidor de autorizacion OAuth2.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value(ConstantsApp.PROPERTY_VALUE_SECRET_CODE_KEY)
	private String jwtKey;
	
	/*
	 * Registrar con @Bean en el contexto de Spring el objeto JwtTokenStore. Método
	 * para crear el token JWT. Para crear el token utiliza la info del objeto
	 * JwtAccessTokenConverter.
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/*
	 * Registrar con @Bean en el contexto de Spring el objeto
	 * JwtAccessTokenConverter. Lo utilizaremos para crear los datos del token y
	 * firmar los tokens. Para ello, añadimos la codigo secreto único (codigo
	 * secreto reforcheck) para la firma.
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		// Añadimos código secreto reforcheck
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}

	/*
	 * Método de configuración para configurar el token con la misma estructura que
	 * el servidor de autorización OAuth2. Configurar el TokenStorage del tipo JWT y
	 * el AccessTokenConverter (igual que en el microservicio OAuth)
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	/*
	 * Método de configuración para proteger las rutas (los endpoints)
	 * 
	 * Acceso a autenticacion: Todos
	 * 
	 * Acceso a consultar todos o el detalle de productos, items y usuarios: Admin y
	 * Users
	 * 
	 * Acceso a crear, modificar y borrar productos, items y usuarios: Admin
	 * 
	 * Cualquier otra ruta que no este configurada: Requiere autenticación
	 * 
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(ConstantsApp.URI_ZUUL_OAUTH).permitAll()
				.antMatchers(HttpMethod.GET, ConstantsApp.URI_ZUUL_GET_ALL_PRODUCTOS,
						ConstantsApp.URI_ZUUL_GET_ALL_ITEMS, ConstantsApp.URI_ZUUL_USUARIOS)
				.permitAll()
				.antMatchers(HttpMethod.GET, ConstantsApp.URI_ZUUL_GET_PRODUCTO, ConstantsApp.URI_ZUUL_GET_ITEM,
						ConstantsApp.URI_ZUUL_USUARIO)
				.hasAnyRole(ConstantsApp.ROL_ADMIN, ConstantsApp.ROL_USER)
				.antMatchers(ConstantsApp.URI_ZUUL_GENERIC_PRODUCTOS, ConstantsApp.URI_ZUUL_GENERIC_ITEMS,
						ConstantsApp.URI_ZUUL_GENERIC_USUARIOS)
				.hasRole(ConstantsApp.ROL_ADMIN).anyRequest().authenticated();
	}

}
