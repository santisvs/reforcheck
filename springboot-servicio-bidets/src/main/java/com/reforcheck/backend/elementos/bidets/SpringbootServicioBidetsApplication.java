package com.reforcheck.backend.elementos.bidets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.reforcheck.backend.commons.constants.ConstantsApp;

/**
 * <b>SpringbootServicioBidetsApplication</b> <br>
 * Clase de arranque del microservicio <b>Bidet</b> para el backend
 * Reforcheck. Este microservicio ofrece toda la funcionalidad relacionada con
 * los elementos Bidets.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@SpringBootApplication
@EntityScan({ ConstantsApp.PACKAGE_ENTITIES_MYSQL })
public class SpringbootServicioBidetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioBidetsApplication.class, args);
	}

}
