package com.reforcheck.backend.units;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.reforcheck.backend.commons.constants.ConstantsApp;

/**
 * <b>SpringbootServicioUnitsApplication</b> <br>
 * Clase de arranque del microservicio <b>Unidad</b> para el backend Reforcheck.
 * Este microservicio ofrece toda la funcionalidad relacionada con las Unidades
 * de trabajo de una reforma.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EnableEurekaClient
@SpringBootApplication
@EntityScan({ ConstantsApp.PACKAGE_ENTITIES_MYSQL })
public class SpringbootServicioUnitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioUnitsApplication.class, args);
	}

}
