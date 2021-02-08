package com.reforcheck.backend.oauth.services;

import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;

/**
 * <b>IOAuthService</b> <br>
 * Interfaz que define los métodos que nos interesen relacionados con la entidad
 * Usuario.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
public interface IOAuthService {

	public Usuario findByEmail(String email);

	public Usuario update(Usuario usuario, Long id);
}
