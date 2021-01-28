package com.reforcheck.backend.elementos.armarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>SpringbootServicioArmariosApplication</b> <br>
 * Clase de arranque del microservicio <b>Armario</b> para el backend
 * Reforcheck. Este microservicio ofrece toda la funcionalidad relacionada con
 * los elementos Armarios.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EntityScan(basePackages = { "com.reforcheck.backend.commons.entities.mysql.models.commons",
		"com.reforcheck.backend.commons.entities.mysql.models.elemento.armario" })
public class SpringbootServicioArmariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioArmariosApplication.class, args);
	}

}
