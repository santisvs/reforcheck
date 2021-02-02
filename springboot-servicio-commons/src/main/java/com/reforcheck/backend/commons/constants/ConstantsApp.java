package com.reforcheck.backend.commons.constants;

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
	public static final String URI_ELEMENTOS = "/repo/";
	public static final String URI_ELEMENTO_ID = "/repo/{id}";
	public static final String URI_ELEMENTOS_ESTANCIA = "/repo/search/estancia/{idEstancia}";
	public static final String URI_ELEMENTOS_FABRICANTE = "/repo/search/fabricante/{referencia}";
	public static final String URI_ELEMENTOS_SEARCH = "/repo/search/buscar";
	public static final String URI_LISTAR = "/listar";
	public static final String URI_VER = "/ver/{id}";
	public static final String URI_CREAR = "/crear";
	public static final String URI_EDITAR = "/editar/{id}";
	public static final String URI_ELIMINAR = "/eliminar/{id}";
	public static final String URI_VER_DETALLE = "/ver/{id}/cantidad/{cantidad}";
	public static final String URI_CONFIG = "/obtener-config";
	public static final String URI_USER_SEARCH = "/usuarios/search/buscar";
	public static final String URI_USER_UPDATE = "/usuarios/{id}";
	public static final String URI_WITHOUT_REQUEST_PARAM = "/";
	public static final String URI_WITH_ID_REQUEST_PARAM = "/{id}";
	public static final String URI_REFERENCIAS_WITHOUT_REQUEST_PARAM = "/referencias/";
	public static final String URI_WITH_REFERENCIA_REQUEST_PARAM = "/referencia/{referencia}";
	public static final String URI_WITH_ESTANCIA_REQUEST_PARAM = "/estancia/{idEstancia}";
	public static final String URI_WITH_PLANTA_REQUEST_PARAM = "/planta/{idPlanta}";
	public static final String URI_WITH_PROPIEDAD_REQUEST_PARAM = "/propiedad/{idPropiedad}";
	
	/*
	 * URI REST
	 */
	public static final String URI_REST_LIST = "/";
	public static final String URI_REST_DETAIL = "/{id}";
	public static final String URI_REST_CREATE = "/";
	public static final String URI_REST_EDIT = "/{id}";
	public static final String URI_REST_DELETE = "/{id}";
	public static final String URI_REST_FIND = "/search/{name}";
	
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
	
	public static final String URI_ZUUL_ARMARIOS = "/api/elementos/armarios";
	public static final String URI_ZUUL_ARMARIO = "/api/elementos/armarios/{id}";
	public static final String URI_ZUUL_GENERIC_ELEMENTOS = "/api/elementos/**";
	
	public static final String URI_ZUUL_PLANTAS = "/api/plantas";
	public static final String URI_ZUUL_PLANTA = "/api/plantas/{id}";
	public static final String URI_ZUUL_GENERIC_PLANTAS = "/api/plantas/**";
	
	public static final String URI_ZUUL_ESTANCIAS = "/api/estancias";
	public static final String URI_ZUUL_ESTANCIA = "/api/estancias/{id}";
	public static final String URI_ZUUL_GENERIC_ESTANCIAS = "/api/estancias/**";
	
	public static final String URI_ZUUL_FABRICANTES = "/api/fabricantes";
	public static final String URI_ZUUL_FABRICANTE = "/api/fabricantes/{id}";
	public static final String URI_ZUUL_GENERIC_FABRICANTES = "/api/fabricantes/**";
	
	public static final String URI_ZUUL_USUARIOS = "/api/usuarios/usuarios";
	public static final String URI_ZUUL_USUARIO = "/api/usuarios/usuarios/{id}";
	public static final String URI_ZUUL_GENERIC_USUARIOS = "/api/usuarios/**";
	
	public static final String URI_ZUUL_UNITS = "/api/units";
	public static final String URI_ZUUL_UNIT = "/api/units/{id}";
	public static final String URI_ZUUL_GENERIC_UNITS = "/api/units/**";

	/*
	 * ROLES
	 */
	public static final String ROL_PREFIX = "ROL_";
	public static final String ROL_OWNER = "OWNER";
	public static final String ROL_REFOR_WORKER = "REFOR_WORKER";
	public static final String ROL_EXT_WORKER = "EXT_WORKER";
	public static final String ROL_ADMIN = "ADMIN";
	public static final String ROL_SUPER_ADMIN = "SUPER_ADMIN";
	
	/*
	 * NAME SERVICES
	 */
	public static final String PRODUCT_SERVICE_NAME = "servicio-productos";
	public static final String USER_SERVICE_NAME = "servicio-usuarios";
	public static final String ESTANCIAS_SERVICE_NAME = "servicio-estancias";
	public static final String ARMARIOS_SERVICE_NAME = "servicio-armarios";
	public static final String BANERAS_SERVICE_NAME = "servicio-baneras";
	public static final String BIDETS_SERVICE_NAME = "servicio-bidets";
	public static final String DUCHAS_SERVICE_NAME = "servicio-duchas";
	public static final String INODOROS_SERVICE_NAME = "servicio-inodoros";
	public static final String LAVABOS_SERVICE_NAME = "servicio-lavabos";
	public static final String VENTANAS_SERVICE_NAME = "servicio-ventanas";
	public static final String PUERTAS_SERVICE_NAME = "servicio-puertas";
	public static final String RADIADORES_SERVICE_NAME = "servicio-radiadores";
	public static final String CLIMATIZACIONES_SERVICE_NAME = "servicio-climatizaciones";
	public static final String ILUMINACIONES_SERVICE_NAME = "servicio-iluminaciones";
	public static final String PINTURAS_SERVICE_NAME = "servicio-pinturas";
	public static final String REVESTIMIENTOS_SERVICE_NAME = "servicio-revestimientos";
	public static final String RODAPIES_SERVICE_NAME = "servicio-rodapies";
	public static final String SOLADOS_SERVICE_NAME = "servicio-solados";
	public static final String TECHOS_SERVICE_NAME = "servicio-techos";
	public static final String MOBILIARIO_OBRAS_SERVICE_NAME = "servicio-mobiliarioobras";
	public static final String INSTALACIONES_SERVICE_NAME = "servicio-instalaciones";
	public static final String FABRICANTES_SERVICE_NAME = "servicio-fabricantes";

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
	public static final String LOG_USER_AUTHORIZED = LOG_MARK + "El usuario autorizado %s tiene los roles %s ";
	public static final String LOG_TRY_LOGIN = LOG_MARK + "Intento de login desde cliente %s";
	public static final String LOG_SUCESS_LOGIN = LOG_MARK + "Sucess Login %s";
	public static final String LOG_FAILURE_LOGIN = LOG_MARK + "Error en el login";
	public static final String LOG_USER_UNABLED = LOG_MARK + "El usuario %s esta deshabilitado (Intento de login fallidos >= 3)";
	public static final String LOG_USER_ATTEMPS_LOGIN = LOG_MARK + "Intentos del usuario %s = %i";

	/*
	 * EXCEPTIONS STRINGS
	 */
	public static final String EXCEPTION_MARK = "--- EXCEPTION_APP: ";
	public static final String EXCEPTION_USER_NO_EXIST = EXCEPTION_MARK + "El usuario %s no existe.";
	public static final String EXCEPTION_ERROR_LOGIN = EXCEPTION_MARK + "Failure Login. Exception: %s";

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
	
	/*
	 * PAQUETES ENTIDADES PARA ESCANEAR POR SPRING BOOT
	 */
	public static final String PACKAGE_ENTITIES_MYSQL = "com.reforcheck.backend.commons.entities.mysql.models";
	public static final String PACKAGE_ENTITIES_POSTGRESQL = "com.reforcheck.backend.commons.entities.postgresql.models";

	/*
	 * ZIPKIN MENSAJES
	 */
	public static final String ZIPKIN_ERROR_MSG = "error.mensaje";
}
