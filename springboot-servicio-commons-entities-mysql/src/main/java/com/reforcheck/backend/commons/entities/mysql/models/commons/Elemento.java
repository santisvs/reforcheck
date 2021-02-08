package com.reforcheck.backend.commons.entities.mysql.models.commons;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

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
@MappedSuperclass
public abstract class Elemento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_elem")
	private String idElem;
	@Column(name = "id_estancia")
	private String idEstancia;
	@Embedded
	private Info info;
	@ElementCollection
	@CollectionTable(name = "referencias_fabricantes")
	private List<String> referenciasFabricantes;
	@Transient
	private List<Fabricante> fabricantes;
	@Embedded
	private Precio precio;

	public Elemento() {
		super();
		this.idElem = ConstantsTypes.STRING_EMPTY;
		this.idEstancia = ConstantsTypes.STRING_EMPTY;
		this.info = new Info();
		this.referenciasFabricantes = null;
		this.fabricantes = null;
		this.precio = new Precio();
	}

	public Elemento(String idElem, String idEstancia, Info info, List<String> referenciasFabricantes,
			List<Fabricante> fabricantes, Precio precio) {
		super();
		this.idElem = idElem;
		this.idEstancia = idEstancia;
		this.info = info;
		this.referenciasFabricantes = referenciasFabricantes;
		this.fabricantes = fabricantes;
		this.precio = precio;
	}

	public void setElemento(Elemento elemento) {
		setIdElem(elemento.getIdElem());
		setIdEstancia(elemento.getIdEstancia());
		setInfo(elemento.getInfo());
		setReferenciasFabricantes(elemento.getReferenciasFabricantes());
		setFabricantes(elemento.getFabricantes());
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

	public String getIdEstancia() {
		return idEstancia;
	}

	public void setIdEstancia(String idEstancia) {
		this.idEstancia = idEstancia;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<String> getReferenciasFabricantes() {
		return referenciasFabricantes;
	}

	public void setReferenciasFabricantes(List<String> referenciasFabricantes) {
		this.referenciasFabricantes = referenciasFabricantes;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricantes.add(fabricante);
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

}
