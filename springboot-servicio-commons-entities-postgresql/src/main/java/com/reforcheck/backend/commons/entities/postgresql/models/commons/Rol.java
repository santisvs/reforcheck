package com.reforcheck.backend.commons.entities.postgresql.models.commons;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <b>Rol</b> <br>
 * Entidad de base de datos con lo siguientes atributos:
 * 
 * <ul>
 * <li>id: Identificador del rol en BBDD</li>
 * <li>name: Nombre del rol</li>
 * <li>usuarios: Lista de usuarios que tienen asignado ese rol</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@Entity
@Table(name = "roles")
public class Rol implements Serializable {

	private static final long serialVersionUID = 882508772978529793L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 30)
	private String nombre;

//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//	private List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public List<Usuario> getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(List<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}
}