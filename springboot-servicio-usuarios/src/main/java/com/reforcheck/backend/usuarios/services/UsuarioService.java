package com.reforcheck.backend.usuarios.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();
	
	public Usuario findByEmail(String email);

	public Usuario findById(Long id);
	
	public List<Usuario> saveAll(List<Usuario> usuarios);

	public Usuario update(Usuario usuario, Long id);

	public void delete(Long id);
	
}
