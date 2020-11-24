package com.practi.app.commons.constants;

public class ConstantsApp {
	
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
	
	/*
	 * NAME SERVICES
	 */
	public static final String PRODUCT_SERVICE_NAME = "servicio-productos";

	
	/*
	 * LOGS STRINGS
	 */
	public static final String LOG_MARK = "--- LOG_APP: ";
	public static final String LOG_FILTER_ZUUL_ROUTE = LOG_MARK + "%s request enrutado a %s";
	public static final String LOG_FILTER_END_TIME = LOG_MARK + "Entrando a post filter";
	public static final String LOG_FILTER_TOTAL_TIME_SECONDS = LOG_MARK + "Tiempo transcurrido en segundos %s seg.";
	public static final String LOG_FILTER_TOTAL_TIME_MILLISECONDS = LOG_MARK + "Tiempo transcurrido en mileseg %s ms.";
	public static final String LOG_PRODUCTO_LISTAR = LOG_MARK + "Petici�n listar productos";
	public static final String LOG_PRODUCTO_VER = LOG_MARK + "Petici�n ver producto con id: ";
	
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
