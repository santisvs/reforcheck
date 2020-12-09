package com.reforcheck.backend.commons.entities.postgresql.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <b>UserApp</b> <br>
 * Entidad de base de datos con lo siguientes atributos:
 * 
 * <ul>
 * <li>id: Identificador del rol en BBDD</li>
 * <li>username: Nickname del usuario</li>
 * <li>password: Password del usuario</li>
 * <li>enabled: Flag que indica si usuario esta activo o no</li>
 * <li>name: Nombre del usuario</li>
 * <li>lastname: Apellido del usuario</li>
 * <li>email: email del usuario</li>
 * <li>{@link com.practi.app.commons.models.entity.Rol}: Rol del usuario</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@Entity
@Table(name = "userapp")
public class UserApp implements Serializable {

	private static final long serialVersionUID = 8068986757802265725L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;
	@Column(length = 60)
	private String password;
	private Boolean enabled;
	private String name;
	private String lastname;
	@Column(unique = true, length = 100)
	private String email;

	@ManyToOne
	private Rol rol;

	private Integer loginAttempts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Integer getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(Integer loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

}