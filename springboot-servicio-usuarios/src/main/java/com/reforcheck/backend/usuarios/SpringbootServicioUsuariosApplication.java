package com.reforcheck.backend.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <b>SpringbootServicioUsuariosApplication</b> <br>
 * Clase de arranque del microservicio <b>Usuario</b> para el backend
 * Reforcheck. Este microservicio ofrece toda la funcionalidad relacionada con
 * los Usuarios.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EntityScan({ "com.reforcheck.backend.commons.entities.postgresql.models.commons",
		"com.reforcheck.backend.commons.entities.postgresql.models.propietario",
		"com.reforcheck.backend.commons.entities.postgresql.models.reforchecker"})
public class SpringbootServicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioUsuariosApplication.class, args);
	}

}
