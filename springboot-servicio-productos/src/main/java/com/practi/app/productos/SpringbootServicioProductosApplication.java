package com.practi.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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
@EntityScan({"com.practi.app.commons.models.entity"})
public class SpringbootServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioProductosApplication.class, args);
	}

}
