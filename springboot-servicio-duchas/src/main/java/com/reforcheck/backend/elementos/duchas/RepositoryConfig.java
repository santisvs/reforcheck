package com.reforcheck.backend.elementos.duchas;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.reforcheck.backend.commons.entities.mysql.models.estancia.Ducha;




@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Ducha.class);
	}

}
