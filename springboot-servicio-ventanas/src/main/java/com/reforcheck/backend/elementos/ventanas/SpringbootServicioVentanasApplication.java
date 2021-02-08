package com.reforcheck.backend.elementos.ventanas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>SpringbootServicioVentanasApplication</b> <br>
 * Clase de arranque del microservicio <b>Ventana</b> para el backend Reforcheck.
 * Este microservicio ofrece toda la funcionalidad relacionada con los elementos
 * Ventanas.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EntityScan(basePackages = { "com.reforcheck.backend.commons.entities.mysql.models.commons",
		"com.reforcheck.backend.commons.entities.mysql.models.elemento.ventana" })
public class SpringbootServicioVentanasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioVentanasApplication.class, args);
	}

}
