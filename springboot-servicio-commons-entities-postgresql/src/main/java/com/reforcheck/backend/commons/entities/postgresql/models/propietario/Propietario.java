package com.reforcheck.backend.commons.entities.postgresql.models.propietario;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.reforcheck.backend.commons.entities.mysql.models.propiedad.Propiedad;
import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;

@Entity
@Table(name = "propietarios")
@PrimaryKeyJoinColumn(name = "usuarioId")
public class Propietario extends Usuario {
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellidos;
	private String dni;
	private String telefono;
	@ElementCollection
	@CollectionTable(name = "referencias_propiedades")
	private List<String> referenciasPropiedades;
	@Transient
	private List<Propiedad> propiedades;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<String> getReferenciasPropiedades() {
		return referenciasPropiedades;
	}
	public void setReferenciasPropiedades(List<String> referenciasPropiedades) {
		this.referenciasPropiedades = referenciasPropiedades;
	}
	public List<Propiedad> getPropiedades() {
		return propiedades;
	}
	public void setPropiedades(List<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}
	
	

}
