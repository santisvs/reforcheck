package com.reforcheck.backend.elementos.armarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.reforcheck.backend.commons.constants.ConstantsApp;



/**
 * <b>SpringbootServicioArmariosApplication</b> <br>
 * Clase de arranque del microservicio <b>Armario</b> para el backend
 * Reforcheck. Este microservicio ofrece toda la funcionalidad relacionada con
 * los elementos Armarios.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@SpringBootApplication
@EntityScan({ ConstantsApp.PACKAGE_ENTITIES_MYSQL })
public class SpringbootServicioArmariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioArmariosApplication.class, args);
	}

}
