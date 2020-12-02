package com.reforcheck.backend.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <b>SpringbootServicioEurekaServerApplication</b> <br>
 * Clase de arranque del microservicios <b>Servidor de Nombre EUREKA></b> para
 * el backend Reforcheck. Este servidor es el encargado de mapear todos los
 * microservicios y sus puertos deonde estan desplegados para enrutar las
 * peticiones recibidas.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringbootServicioEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioEurekaServerApplication.class, args);
	}

}
