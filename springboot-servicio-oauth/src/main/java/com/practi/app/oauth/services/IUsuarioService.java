package com.practi.app.oauth.services;

import com.practi.app.commons.models.entity.Usuario;

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
