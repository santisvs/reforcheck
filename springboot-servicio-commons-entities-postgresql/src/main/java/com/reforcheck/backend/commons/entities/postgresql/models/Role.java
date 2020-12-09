package com.reforcheck.backend.commons.entities.postgresql.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 882508772978529793L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 30)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listRole")
	private List<UserApp> listUsersApp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserApp> getListUsersApp() {
		return listUsersApp;
	}

	public void setListUsersApp(List<UserApp> listUsersApp) {
		this.listUsersApp = listUsersApp;
	}

	
}
