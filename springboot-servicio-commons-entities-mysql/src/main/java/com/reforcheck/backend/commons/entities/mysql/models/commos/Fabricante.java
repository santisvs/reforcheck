package com.reforcheck.backend.commons.entities.mysql.models.commos;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;

/**
 * 
 * <b>Fabricante</b> <br>
 * Clase que define todas las propiedades relacionadas con el material.
 * 
 * <ul>
 * <li>referencia: Código de referencia del producto necesario para ejecutar un elemento</li>
 * <li>precio: Precio del producto para ejecutar el elemento</li>
 * <li>nombre: Nombre del fabricante del producto</li>
 * <li>direccion: Dirección postal del fabricante del producto</li>
 * <li>url: Url de la web del fabricante del producto</li>
 * <li>telefono: Teléfono del fabricante del producto</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "fabricantes")
public class Fabricante {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

	private String referencia;
	@Embedded
	private Precio precio;
	private String nombre;
	private String direccion;
	private String url;
	private String telefono;

	public Fabricante() {
		super();
		this.referencia = ConstantsTypes.STRING_EMPTY;
		this.precio = new Precio();
		this.nombre = ConstantsTypes.STRING_EMPTY;
		this.direccion = ConstantsTypes.STRING_EMPTY;
		this.url = ConstantsTypes.STRING_EMPTY;
		this.telefono = ConstantsTypes.STRING_EMPTY;
	}

	public Fabricante(String referencia, Precio precio, String nombre, String direccion, String url, String telefono) {
		super();
		this.referencia = referencia;
		this.precio = precio;
		this.nombre = nombre;
		this.direccion = direccion;
		this.url = url;
		this.telefono = telefono;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
