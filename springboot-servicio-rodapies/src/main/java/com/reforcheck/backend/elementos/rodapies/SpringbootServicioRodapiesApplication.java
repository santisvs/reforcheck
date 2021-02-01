package com.reforcheck.backend.elementos.rodapies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>SpringbootServicioRodapiesApplication</b> <br>
 * Clase de arranque del microservicio <b>Rodapie</b> para el backend
 * Reforcheck. Este microservicio ofrece toda la funcionalidad relacionada con
 * los elementos Rodapies.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EntityScan(basePackages = { "com.reforcheck.backend.commons.entities.mysql.models.commons",
		"com.reforcheck.backend.commons.entities.mysql.models.common.unod",
		"com.reforcheck.backend.commons.entities.mysql.models.elemento.rodapie" })
public class SpringbootServicioRodapiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioRodapiesApplication.class, args);
	}

}
