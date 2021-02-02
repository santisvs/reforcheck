package com.reforcheck.backend.commons.entities.postgresql.models.commons;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

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
@MappedSuperclass
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_usuario")
	private String idUsuario;
	private Boolean activo;
	@Column(unique = true, length = 100)
	private String email;
	@Column(length = 60)
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name="usuario_id"), 
	inverseJoinColumns = @JoinColumn(name="rol_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})})
	private List<Rol> roles;
	@Transient
	private Integer intentosAcceso;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
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
	public List<Rol> getRoles() {
		return roles;
	}
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	public Integer getIntentosAcceso() {
		return intentosAcceso;
	}
	public void setIntentosAcceso(Integer intentosAcceso) {
		this.intentosAcceso = intentosAcceso;
	}
	
	

}
