package com.practi.app.commons.constants;

public class ConstantsApp {

	/*
	 * CLIENTES DEL BACKEND (Ids de apps frontend clientes del backend)
	 */
	public static final String FRONT_CLIENT_DEFAULT_NAME = "frontendapp";
	public static final String FRONT_CLIENT_DEFAULT_PASSW = "1234";
	//public static final String FRONT_CLIENT_ANGULAR_APP = "angularapp";

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

	/*
	 * EXCEPTIONS STRINGS
	 */
	public static final String EXCEPTION_MARK = "--- EXCEPTION_APP: ";
	public static final String EXCEPTION_USER_NO_EXIST = EXCEPTION_MARK + "El usuario %s no existe.";

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
}
