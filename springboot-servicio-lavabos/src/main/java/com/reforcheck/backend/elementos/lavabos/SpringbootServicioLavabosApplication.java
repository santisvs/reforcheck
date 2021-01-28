package com.reforcheck.backend.elementos.lavabos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>SpringbootServicioLavabosApplication</b> <br>
 * Clase de arranque del microservicio <b>Lavabo</b> para el backend Reforcheck.
 * Este microservicio ofrece toda la funcionalidad relacionada con los elementos
 * Lavabos.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EntityScan(basePackages = { "com.reforcheck.backend.commons.entities.mysql.models.commons",
		"com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo" })
public class SpringbootServicioLavabosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioLavabosApplication.class, args);
	}

}
