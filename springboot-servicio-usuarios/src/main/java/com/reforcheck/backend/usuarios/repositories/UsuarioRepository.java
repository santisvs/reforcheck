package com.reforcheck.backend.usuarios.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;


public interface UsuarioRepository<T extends Usuario> extends PagingAndSortingRepository<Usuario, Long> {

	public T findByEmail(String email);

}
