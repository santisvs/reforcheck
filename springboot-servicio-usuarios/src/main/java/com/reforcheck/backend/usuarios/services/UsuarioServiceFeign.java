package com.reforcheck.backend.usuarios.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;
import com.reforcheck.backend.commons.entities.postgresql.models.propietario.Propietario;
import com.reforcheck.backend.commons.entities.postgresql.models.reforchecker.Reforchecker;
import com.reforcheck.backend.usuarios.repositories.UsuarioRepository;

@Service("serviceFeign")
public class UsuarioServiceFeign implements UsuarioService {

	@Autowired
	private UsuarioRepository<?> usuarioRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return (Usuario) usuarioRepository.findById(id).get();
	}

	@Override
	@Transactional
	public List<Usuario> saveAll(List<Usuario> usuarios) {
		List<Usuario> resultado = new ArrayList<Usuario>();
		for (Usuario usuario : usuarios) {
			if (null == usuarioRepository.findByEmail(usuario.getEmail())) {
				resultado.add((Usuario) usuarioRepository.save(usuario));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Usuario update(Usuario usuario, Long id) {
		Usuario usuarioRepo = (Usuario) usuarioRepository.findById(id).get();
		if(usuarioRepo instanceof Propietario) {
			((Propietario) usuarioRepo).setPropietario((Propietario) usuario);
		}
		if(usuarioRepo instanceof Reforchecker) {
			((Reforchecker) usuarioRepo).setReforchecker((Reforchecker) usuario);
		}
		return (Usuario) usuarioRepository.save(usuarioRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}

}
