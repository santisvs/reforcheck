package com.reforcheck.backend.commons.entities.mysql.models.commos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;

/**
 * 
 * <b>Elemento</b> <br>
 * Clase que define los atributos comunes de un elemento (tarea).
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
@Table(name = "elementos")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Elemento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_elem")
	private String idElem;
	@Embedded
	private Info info;
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Fabricante fabricante;
	@Embedded
	private Precio precio;

	public Elemento() {
		super();
		this.idElem = ConstantsTypes.STRING_EMPTY;
		this.info = new Info();
		this.fabricante = new Fabricante();
		this.precio = new Precio();
	}

	public Elemento(String idElem, Info info, Fabricante fabricante, Precio precio) {
		super();
		this.idElem = idElem;
		this.info = info;
		this.fabricante = fabricante;
		this.precio = precio;
	}

	public void setElemento(Elemento elemento) {
		setIdElem(elemento.getIdElem());
		setInfo(elemento.getInfo());
		setFabricante(elemento.getFabricante());
		setPrecio(elemento.getPrecio());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdElem() {
		return idElem;
	}

	public void setIdElem(String idElem) {
		this.idElem = idElem;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

}
