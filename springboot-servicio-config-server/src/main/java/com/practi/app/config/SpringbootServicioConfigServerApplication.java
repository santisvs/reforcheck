package com.practi.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * <b>SpringbootServicioConfigServerApplication</b> <br>
 * Clase de arranque del microservicios <b>Servidor de Configuración></b> para
 * el backend Reforcheck. Este servidor es el encargado de acceder al proyecto
 * <b>practi-config</b> con todas las configuraciones externas del proyecto.
 * 
 * <ul>
 * <li>Configuraciones de entornos</li>
 * <li>Configuraciones de conexiones a BBDD por cada entorno</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableConfigServer
@SpringBootApplication
public class SpringbootServicioConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioConfigServerApplication.class, args);
	}

}
