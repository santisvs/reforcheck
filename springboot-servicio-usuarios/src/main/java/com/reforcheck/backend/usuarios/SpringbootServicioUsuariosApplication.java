package com.reforcheck.backend.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.reforcheck.backend.commons.constants.ConstantsApp;

/**
 * <b>SpringbootServicioUsuariosApplication</b> <br>
 * Clase de arranque del microservicio <b>Usuario</b> para el backend Reforcheck.
 * Este microservicio ofrece toda la funcionalidad relacionada con los Usuarios.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@EntityScan({ConstantsApp.PACKAGE_ENTITIES_POSTGRESQL})
@SpringBootApplication
public class SpringbootServicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioUsuariosApplication.class, args);
	}

}
