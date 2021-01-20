package com.reforcheck.backend.elementos.duchas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.reforcheck.backend.commons.constants.ConstantsApp;

/**
 * <b>SpringbootServicioDuchasApplication</b> <br>
 * Clase de arranque del microservicio <b>Ducha</b> para el backend
 * Reforcheck. Este microservicio ofrece toda la funcionalidad relacionada con
 * los elementos Duchas.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@SpringBootApplication
@EntityScan({ ConstantsApp.PACKAGE_ENTITIES_MYSQL })
public class SpringbootServicioDuchasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioDuchasApplication.class, args);
	}

}
