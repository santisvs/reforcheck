package com.reforcheck.backend.elementos.puertas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>SpringbootServicioPuertasApplication</b> <br>
 * Clase de arranque del microservicio <b>Puerta</b> para el backend Reforcheck.
 * Este microservicio ofrece toda la funcionalidad relacionada con los elementos
 * Puertas.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EntityScan(basePackages = { "com.reforcheck.backend.commons.entities.mysql.models.commons",
		"com.reforcheck.backend.commons.entities.mysql.models.elemento.puerta" })
public class SpringbootServicioPuertasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioPuertasApplication.class, args);
	}

}
