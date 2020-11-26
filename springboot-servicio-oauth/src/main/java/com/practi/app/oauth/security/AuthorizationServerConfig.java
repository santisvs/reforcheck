package com.practi.app.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.practi.app.commons.constants.ConstantsApp;

/**
 * <b>AuthorizationServerConfig</b> <br>
 * Clase para configurar el servidor de autorización. Este servidor de
 * autorización se encarga del proceso del LOGIN por el lado de OAuth2.
 * 
 * <ul>
 * <li>Autenticación</li>
 * <li>Generar el token JWT</li>
 * <li>Validarlo</li>
 * </ul>
 * 
 * Para ello utiliza el bean AuthenticationManager creado en la clase
 * {@link com.practi.app.oauth.security.SpringSecurityConfig}
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */

/*
 * Habilitamos la clase como servidor de autorización.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	// Inyectar los beans de spring
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

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
		tokenConverter.setSigningKey("codigo_secreto_reforcheck_1111");
		return tokenConverter;
	}

	/*
	 * Configurar la seguridad que tienen nuestros endpoints en el servidor de
	 * autorización OAuth2 para generar el token y validar el token
	 * 
	 * -tokenKeyAccess: Endpoint para generar el token. Permitimos a cualquiera
	 * acceder al endpoint para generar un token de acceso al backend.
	 * 
	 * -checkTokenAccess: Valida el token
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess(ConstantsApp.SPRING_SECURITY_METHOD_ALLOW_ALL_GET_TOKEN)
				.checkTokenAccess(ConstantsApp.SPRING_SECURITY_METHOD_IS_AUTHENTICATED_CLIENT);
	}

	/*
	 * Método para registrar nuestros clientes. Las aplicaciones frontend que se van
	 * a conectar a nuestros microservicios.
	 * 
	 * Debemos registrar cada uno de nuestros clientes (ejemplo: App angular, App
	 * IOS, Software externo...) con la siguiente info:
	 * 
	 * - id: Nombre del cliente - secret: password del cliente - scopes: permisos
	 * asignados a ese cliente contra el backend - authorizedGrantTypes: indicamos
	 * que utilizamos el password para obtener el token. Utilizamos password porque
	 * nuestros usuarios existen en nuestro backend y para autenticar utilizamos el
	 * username y el password. - accessTokenValiditySeconds: tiempo de validez del
	 * token en segundos (3600 seg = 1hora) - refreshTokenValiditySeconds: tiempo de
	 * validez del refreshtoken en segundos (3600 seg = 1hora)
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		/*
		 * Registrar el cliente default en memoria con su password (secret) y los
		 * permisos
		 */
		clients.inMemory().withClient(ConstantsApp.FRONT_CLIENT_DEFAULT_NAME)
				.secret(passwordEncoder.encode(ConstantsApp.FRONT_CLIENT_DEFAULT_NAME))
				.scopes(ConstantsApp.FRONT_CLIENT_SCOPE_READ, ConstantsApp.FRONT_CLIENT_SCOPE_WRITE)
				.authorizedGrantTypes(ConstantsApp.AUTHORIZED_TYPE_PASSW, ConstantsApp.AUTHORIZED_TYPE_REFRESH)
				.accessTokenValiditySeconds(ConstantsApp.AUTHORIZED_TIME_TOKEN)
				.refreshTokenValiditySeconds(ConstantsApp.AUTHORIZED_TIME_TOKEN);
		// Incluir más clientes
		// .and()
		// .withClient(ConstantsApp.FRONT_CLIENT_ANGULAR_APP)
		// .secret(passwordEncoder.encode(ConstantsApp.FRONT_CLIENT_DEFAULT_NAME))
		// .scopes(ConstantsApp.FRONT_CLIENT_SCOPE_READ,
		// ConstantsApp.FRONT_CLIENT_SCOPE_WRITE)
		// .authorizedGrantTypes(ConstantsApp.AUTHORIZED_TYPE_PASSW,
		// ConstantsApp.AUTHORIZED_TYPE_REFRESH)
		// .accessTokenValiditySeconds(ConstantsApp.AUTHORIZED_TIME_TOKEN)
		// .refreshTokenValiditySeconds(ConstantsApp.AUTHORIZED_TIME_TOKEN);

	}

	/*
	 * Método para configurar: AuthenticationManager, TokenStorage del tipo JWT y
	 * AccessTokenConverter que guarda los datos de usuario (username y rol) y los
	 * claims (datos extra que queramos guardar) en el token, codificados en base64.
	 * 
	 * @Param endpoints: Esta relacionado al endpoint del server OAuth2 (servidor de
	 * autorización) que se encarga de generar el token.
	 * 
	 * El endpoint es oauthToken del tipo HTTP POST
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		// Registrar el authenticationManager
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter());

	}

}
