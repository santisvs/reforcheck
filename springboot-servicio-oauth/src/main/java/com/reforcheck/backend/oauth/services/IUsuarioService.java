package com.reforcheck.backend.oauth.services;

import com.reforcheck.backend.commons.entities.postgresql.models.Usuario;

/**
 * <b>IUsuarioService</b> <br>
 * Interfaz que define los métodos que nos interesen relacionados con la entidad
 * Usuario.
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
public interface IUsuarioService {

	public Usuario findByUsername(String username);

	public Usuario update(Usuario usuario, Long id);
}
