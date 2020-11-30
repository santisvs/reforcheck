package com.practi.app.commons.constants;

public class ConstantsApp {

	/*
	 * SCOPES CLIENTS DEL BACKEND
	 */
	public static final String FRONT_CLIENT_SCOPE_READ = "read";
	public static final String FRONT_CLIENT_SCOPE_WRITE = "write";
	
	/*
	 * TIPOS DE AUTENTICACION DE CLIENTES AL BACKEND (authorizedGrantTypes)
	 */
	public static final String AUTHORIZED_TYPE_PASSW = "password";
	public static final String AUTHORIZED_TYPE_CODE = "authorization_code";
	public static final String AUTHORIZED_TYPE_IMPLICIT = "implicit";
	public static final String AUTHORIZED_TYPE_REFRESH = "refresh_token";
	public static final int AUTHORIZED_TIME_TOKEN = 3600;
	
	/*
	 * SEGURIDAD PARA ACCEDER A NUESTRO SERVIDOR OAUTH2
	 */
	public static final String SPRING_SECURITY_METHOD_ALLOW_ALL_GET_TOKEN = "permitAll()";
	public static final String SPRING_SECURITY_METHOD_IS_AUTHENTICATED_CLIENT = "isAuthenticated()";
	
	
	/*
	 * URI SERVICES
	 */
	public static final String URI_LISTAR = "/listar";
	public static final String URI_VER = "/ver/{id}";
	public static final String URI_CREAR = "/crear";
	public static final String URI_EDITAR = "/editar/{id}";
	public static final String URI_ELIMINAR = "/eliminar/{id}";
	public static final String URI_VER_DETALLE = "/ver/{id}/cantidad/{cantidad}";
	public static final String URI_CONFIG = "/obtener-config";
	public static final String URI_USER_SEARCH = "/usuarios/search/buscar";
	
	/*
	 * URI ZUUL SERVICES
	 */
	public static final String URI_ZUUL_ALL_URIS = "/**";
	public static final String URI_ZUUL_OAUTH = "/api/security/oauth/**";
	
	public static final String URI_ZUUL_GET_ALL_PRODUCTOS = "/api/productos" + URI_LISTAR;
	public static final String URI_ZUUL_GET_PRODUCTO = "/api/productos" + URI_VER;
	public static final String URI_ZUUL_CREAR_PRODUCTO = "/api/productos" + URI_CREAR;
	public static final String URI_ZUUL_EDITAR_PRODUCTO = "/api/productos" + URI_EDITAR;
	public static final String URI_ZUUL_ELIMINAR_PRODUCTO = "/api/productos" + URI_ELIMINAR;
	public static final String URI_ZUUL_GENERIC_PRODUCTOS = "/api/productos/**";
	
	public static final String URI_ZUUL_GET_ALL_ITEMS = "/api/items" + URI_LISTAR;
	public static final String URI_ZUUL_GET_ITEM = "/api/items" + URI_VER_DETALLE;
	public static final String URI_ZUUL_CREAR_ITEM = "/api/items" + URI_CREAR;
	public static final String URI_ZUUL_EDITAR_ITEM = "/api/items" + URI_EDITAR;
	public static final String URI_ZUUL_ELIMINAR_ITEM = "/api/items" + URI_ELIMINAR;
	public static final String URI_ZUUL_GENERIC_ITEMS = "/api/items/**";
	
	public static final String URI_ZUUL_USUARIOS = "/api/usuarios/usuarios";
	public static final String URI_ZUUL_USUARIO = "/api/usuarios/usuarios/{id}";
	public static final String URI_ZUUL_GENERIC_USUARIOS = "/api/usuarios/**";

	/*
	 * ROLES
	 */
	public static final String ROL_PREFIX = "ROL_";
	public static final String ROL_ADMIN = "ADMIN";
	public static final String ROL_USER = "USER";
	
	/*
	 * NAME SERVICES
	 */
	public static final String PRODUCT_SERVICE_NAME = "servicio-productos";
	public static final String USER_SERVICE_NAME = "servicio-usuarios";

	/*
	 * LOGS STRINGS
	 */
	public static final String LOG_MARK = "--- LOG_APP: ";
	public static final String LOG_FILTER_ZUUL_ROUTE = LOG_MARK + "%s request enrutado a %s";
	public static final String LOG_FILTER_END_TIME = LOG_MARK + "Entrando a post filter";
	public static final String LOG_FILTER_TOTAL_TIME_SECONDS = LOG_MARK + "Tiempo transcurrido en segundos %s seg.";
	public static final String LOG_FILTER_TOTAL_TIME_MILLISECONDS = LOG_MARK + "Tiempo transcurrido en mileseg %s ms.";
	public static final String LOG_PRODUCTO_LISTAR = LOG_MARK + "Petición listar productos";
	public static final String LOG_PRODUCTO_VER = LOG_MARK + "Petición ver producto con id: ";
	public static final String LOG_USER_AUTHORIZED = LOG_MARK + "El usuario autorizado %s tiene el rol %s ";
	public static final String LOG_SUCESS_LOGIN = LOG_MARK + "Sucess Login %s";

	/*
	 * EXCEPTIONS STRINGS
	 */
	public static final String EXCEPTION_MARK = "--- EXCEPTION_APP: ";
	public static final String EXCEPTION_USER_NO_EXIST = EXCEPTION_MARK + "El usuario %s no existe.";
	public static final String EXCEPTION_ERROR_LOGIN = EXCEPTION_MARK + "Failure Login s%. Exception: %s";

	/*
	 * APP VARIABLES
	 */
	public static final String INIT_TIME = "initTime";

	/*
	 * APP PROPERTIES
	 */
	public static final String PROPERTY_PORT = "${server.port}";
	public static final String PROPERTY_TEXTO = "${configuracion.texto}";
	public static final String PROPERTY_AUTOR_NAME = "configuracion.autor.nombre";
	public static final String PROPERTY_AUTOR_EMAIL = "configuracion.autor.email";
	
	/*
	 * CLIENTES DEL BACKEND (Ids de apps frontend clientes del backend)
	 */
	public static final String PROPERTY_FRONT_CLIENT_DEFAULT_NAME = "config.security.oauth.client.id";
	public static final String PROPERTY_FRONT_CLIENT_DEFAULT_PASSW = "config.security.oauth.client.secret";
	public static final String PROPERTY_SECRET_CODE_KEY = "config.security.oauth.jwt.key";
	public static final String PROPERTY_VALUE_SECRET_CODE_KEY = "${config.security.oauth.jwt.key}";
	//public static final String FRONT_CLIENT_ANGULAR_APP = "angularapp";
	
	/*
	 * CORS
	 */
	public static final String CORS_ALLOW_ORIGIN_ALL = "*";
	
	/*
	 * PALABRAS ESPECIALES
	 */
	public static final String WORD_AUTHORIZATION = "Authorization";
	public static final String WORD_CONTENT_TYPE = "Content-Type";
	
}
