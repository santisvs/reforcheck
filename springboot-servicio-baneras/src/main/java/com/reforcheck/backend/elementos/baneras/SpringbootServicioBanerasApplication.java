package com.reforcheck.backend.elementos.baneras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.reforcheck.backend.commons.constants.ConstantsApp;

/**
 * <b>SpringbootServicioBanerasApplication</b> <br>
 * Clase de arranque del microservicio <b>Baneras</b> para el backend
 * Reforcheck. Este microservicio ofrece toda la funcionalidad relacionada con
 * los elementos Bañeras.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@SpringBootApplication
@EntityScan({ ConstantsApp.PACKAGE_ENTITIES_MYSQL })
public class SpringbootServicioBanerasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioBanerasApplication.class, args);
	}

}
