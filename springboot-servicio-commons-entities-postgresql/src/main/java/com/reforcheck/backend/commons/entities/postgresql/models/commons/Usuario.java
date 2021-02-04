package com.reforcheck.backend.commons.entities.postgresql.models.commons;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reforcheck.backend.commons.constants.ConstantsTypes;

/**
 * 
 * <b>Usuario</b> <br>
 * Clase que define los atributos comunes de un usuario del sistema.
 * 
 * <ul>
 * <li>idElem: Identificador del sistema único para ese elemento</li>
 * <li>info: Información adicional para ese elemento</li>
 * <li>fabricante: Información sobre el fabricante</li>
 * <li>precio: Precio del elemento o tarea</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public abstract class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean activo;
	@Column(unique = true, length = 100)
	private String email;
	@Column(length = 60)
	private String password;
	@Column(name = "intentos_acceso")
	private Integer intentosAcceso;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "usuario_id", "rol_id" }) })
	private List<Rol> roles;

	public Usuario() {
		super();
		this.id = null;
		this.activo = false;
		this.email = ConstantsTypes.STRING_EMPTY;
		this.password = ConstantsTypes.STRING_EMPTY;
		this.intentosAcceso = ConstantsTypes.ENT_0;
		this.roles = null;
	}

	public Usuario(Long id, Boolean activo, String email, String password, Integer intentosAcceso, List<Rol> roles) {
		super();
		this.id = id;
		this.activo = activo;
		this.email = email;
		this.password = password;
		this.intentosAcceso = intentosAcceso;
		this.roles = roles;
	}
	
	public Usuario(Boolean activo, String email, String password, Integer intentosAcceso, List<Rol> roles) {
		super();
		this.id = null;
		this.activo = activo;
		this.email = email;
		this.password = password;
		this.intentosAcceso = intentosAcceso;
		this.roles = roles;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIntentosAcceso() {
		return intentosAcceso;
	}

	public void setIntentosAcceso(Integer intentosAcceso) {
		this.intentosAcceso = intentosAcceso;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

}
