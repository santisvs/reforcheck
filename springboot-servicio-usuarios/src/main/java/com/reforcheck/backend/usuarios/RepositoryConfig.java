package com.reforcheck.backend.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.reforcheck.backend.commons.entities.postgresql.models.Role;
import com.reforcheck.backend.commons.entities.postgresql.models.UserApp;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(UserApp.class, Role.class);
	}

}
