package com.reforcheck.backend.commons.entities.mysql.models.planta;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commons.TresD;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Estancia;

/**
 * 
 * <b>Planta</b> <br>
 * Clase que las características de una planta perteneciente a una propiedad.
 * 
 * <ul>
 * <li>id: identificación de bbdd</li>
 * <li>idPlanta: identificación de una planta en el sistema</li>
 * <li>nombre: Nombre de la planta</li>
 * <li>numero: Número de planta</li>
 * <li>medida: Tamaño de la planta</li>
 * <li>info: Información adicional de la planta</li>
 * <li>precio: Coste de la reforma de esa planta</li>
 * <li>estancias: Lista de estancias que tiene la planta</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "plantas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Planta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_planta")
	private String idPlanta;
	@Column(name = "id_propiedad")
	private String idPropiedad;
	private String nombre;
	private Integer numero;
	@Embedded
	private TresD medida;
	@Embedded
	private Info info;
	@Embedded
	private Precio precio;
	@Transient
	private List<Estancia> estancias;

	public Planta() {
		super();
		this.idPlanta = ConstantsTypes.STRING_EMPTY;
		this.idPropiedad = ConstantsTypes.STRING_EMPTY;
		this.nombre = ConstantsTypes.STRING_EMPTY;
		this.numero = ConstantsTypes.ENT_0;
		this.medida = new TresD();
		this.info = new Info();
		this.precio = new Precio();
		this.estancias = null;
	}

	public Planta(String idPlanta, String idPropiedad, String nombre, Integer numero, TresD medida, Info info,
			Precio precio, List<Estancia> estancias) {
		super();
		this.idPlanta = idPlanta;
		this.idPropiedad = idPropiedad;
		this.nombre = nombre;
		this.numero = numero;
		this.medida = medida;
		this.info = info;
		this.precio = precio;
		setEstancias(estancias);
	}
	
	public Planta(Planta planta) {
		setIdPlanta(planta.getIdPlanta());
		setIdPropiedad(planta.getIdPropiedad());
		setNombre(planta.getNombre());
		setNumero(planta.getNumero());
		setMedida(planta.getMedida());
		setInfo(planta.getInfo());
		setPrecio(planta.getPrecio());
		setEstancias(planta.getEstancias());
	}
	
	public void setPlanta(Planta planta) {
		setIdPlanta(planta.getIdPlanta());
		setIdPropiedad(planta.getIdPropiedad());
		setNombre(planta.getNombre());
		setNumero(planta.getNumero());
		setMedida(planta.getMedida());
		setInfo(planta.getInfo());
		setPrecio(planta.getPrecio());
		setEstancias(planta.getEstancias());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(String idPlanta) {
		this.idPlanta = idPlanta;
	}

	public String getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(String idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public TresD getMedida() {
		return medida;
	}

	public void setMedida(TresD medida) {
		this.medida = medida;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public List<Estancia> getEstancias() {
		return estancias;
	}

	public void setEstancias(List<Estancia> estancias) {
		this.estancias = estancias;
	}

}
