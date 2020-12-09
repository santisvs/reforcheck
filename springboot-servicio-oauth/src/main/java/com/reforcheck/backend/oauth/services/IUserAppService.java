package com.reforcheck.backend.oauth.services;

import com.reforcheck.backend.commons.entities.postgresql.models.UserApp;

/**
 * <b>IUsuarioService</b> <br>
 * Interfaz que define los métodos que nos interesen relacionados con la entidad
 * Usuario.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
public interface IUserAppService {

	public UserApp findByUsername(String username);

	public UserApp update(UserApp usuario, Long id);
}
