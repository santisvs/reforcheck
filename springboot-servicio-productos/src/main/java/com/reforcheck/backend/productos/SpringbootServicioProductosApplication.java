package com.reforcheck.backend.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.reforcheck.backend.commons.constants.ConstantsApp;

/**
 * <b>SpringbootServicioProductosApplication</b> <br>
 * Clase de arranque del microservicio <b>Producto</b> para el backend Reforcheck.
 * Este microservicio ofrece toda la funcionalidad relacionada con los Productos.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableEurekaClient
@SpringBootApplication
@EntityScan({ConstantsApp.PACKAGE_ENTITIES_MYSQL})
public class SpringbootServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioProductosApplication.class, args);
	}

}
