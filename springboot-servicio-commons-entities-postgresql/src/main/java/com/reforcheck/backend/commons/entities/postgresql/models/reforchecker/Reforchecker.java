package com.reforcheck.backend.commons.entities.postgresql.models.reforchecker;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.propiedad.Propiedad;
import com.reforcheck.backend.commons.entities.postgresql.models.commons.Rol;
import com.reforcheck.backend.commons.entities.postgresql.models.commons.TipoUsuario;
import com.reforcheck.backend.commons.entities.postgresql.models.commons.Usuario;

@Entity
@DiscriminatorValue("reforchecker")
@PrimaryKeyJoinColumn(name = "usuarioId")
public class Reforchecker extends Usuario {

	private static final long serialVersionUID = 1L;

	private TipoUsuario tipo;
	private String nombre;
	private String apellidos;
	private String dni;
	private String telefono;
	private String nomina;
	@ElementCollection
	@CollectionTable(name = "referencias_trabajos")
	private List<String> referenciasPropiedades;
	@Transient
	private List<Propiedad> propiedades;

	public Reforchecker() {
		super();
		this.tipo = TipoUsuario.SIN_DEFINIR;
		this.nombre = ConstantsTypes.STRING_EMPTY;
		this.apellidos = ConstantsTypes.STRING_EMPTY;
		this.dni = ConstantsTypes.STRING_EMPTY;
		this.telefono = ConstantsTypes.STRING_EMPTY;
		this.nomina = ConstantsTypes.STRING_EMPTY;
		this.referenciasPropiedades = null;
		this.propiedades = null;
	}

	public Reforchecker(Boolean activo, String email, String password, Integer intentosAcceso, List<Rol> roles) {
		super(activo, email, password, intentosAcceso, roles);
		this.tipo = TipoUsuario.SIN_DEFINIR;
		this.nombre = ConstantsTypes.STRING_EMPTY;
		this.apellidos = ConstantsTypes.STRING_EMPTY;
		this.dni = ConstantsTypes.STRING_EMPTY;
		this.telefono = ConstantsTypes.STRING_EMPTY;
		this.nomina = ConstantsTypes.STRING_EMPTY;
		this.referenciasPropiedades = null;
		this.propiedades = null;
	}

	public Reforchecker(Long id, Boolean activo, String email, String password, Integer intentosAcceso,
			List<Rol> roles) {
		super(id, activo, email, password, intentosAcceso, roles);
		this.tipo = TipoUsuario.SIN_DEFINIR;
		this.nombre = ConstantsTypes.STRING_EMPTY;
		this.apellidos = ConstantsTypes.STRING_EMPTY;
		this.dni = ConstantsTypes.STRING_EMPTY;
		this.telefono = ConstantsTypes.STRING_EMPTY;
		this.nomina = ConstantsTypes.STRING_EMPTY;
		this.referenciasPropiedades = null;
		this.propiedades = null;
	}

	public Reforchecker(Boolean activo, String email, String password, Integer intentosAcceso, List<Rol> roles,
			TipoUsuario tipo, String nombre, String apellidos, String dni, String telefono, String nomina,
			List<String> referenciasPropiedades, List<Propiedad> propiedades) {
		super(activo, email, password, intentosAcceso, roles);
		this.tipo = tipo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.nomina = nomina;
		this.referenciasPropiedades = referenciasPropiedades;
		this.propiedades = propiedades;
	}

	public Reforchecker(Long id, Boolean activo, String email, String password, Integer intentosAcceso, List<Rol> roles,
			TipoUsuario tipo, String nombre, String apellidos, String dni, String telefono, String nomina,
			List<String> referenciasPropiedades, List<Propiedad> propiedades) {
		super(id, activo, email, password, intentosAcceso, roles);
		this.tipo = tipo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.nomina = nomina;
		this.referenciasPropiedades = referenciasPropiedades;
		this.propiedades = propiedades;
	}

	public void setReforchecker(Reforchecker reforchecker) {
		setTipo(reforchecker.getTipo());
		setNombre(reforchecker.getNombre());
		setApellidos(reforchecker.getApellidos());
		setDni(reforchecker.getDni());
		setTelefono(reforchecker.getTelefono());
		setNomina(reforchecker.getNomina());
		setReferenciasPropiedades(reforchecker.getReferenciasPropiedades());
		setPropiedades(reforchecker.getPropiedades());
		setActivo(reforchecker.getActivo());
		setEmail(reforchecker.getEmail());
		setPassword(reforchecker.getPassword());
		setIntentosAcceso(reforchecker.getIntentosAcceso());
		setRoles(reforchecker.getRoles());
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

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

	public String getNomina() {
		return nomina;
	}

	public void setNomina(String nomina) {
		this.nomina = nomina;
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
