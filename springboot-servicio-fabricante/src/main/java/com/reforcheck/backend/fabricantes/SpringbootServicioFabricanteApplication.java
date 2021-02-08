package com.reforcheck.backend.fabricantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <b>SpringbootServicioFabricanteApplication</b> <br>
 * Clase de arranque del microservicio <b>Fabricante</b> para el backend
 * Reforcheck. Este microservicio ofrece toda la funcionalidad relacionada con
 * los Fabricantes.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackages = { "com.reforcheck.backend.commons.entities.mysql.models.commons",
		"com.reforcheck.backend.commons.entities.mysql.models.fabricante" })
public class SpringbootServicioFabricanteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioFabricanteApplication.class, args);
	}

}
