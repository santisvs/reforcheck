package com.reforcheck.backend.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <b>SpringbootServicioZuulServerApplication</b> <br>
 * Clase de arranque del microservicios <b>Servidor enrutador ZUUL></b> para el
 * backend Reforcheck. Este servidor es el encargado de recoger todas las
 * peticiones a la API y enrutar estas peticiones a los distintos microservicios
 * utilizando EUREKA.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class SpringbootServicioZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioZuulServerApplication.class, args);
	}

}
