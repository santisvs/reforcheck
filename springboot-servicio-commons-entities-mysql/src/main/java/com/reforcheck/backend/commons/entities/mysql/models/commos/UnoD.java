package com.reforcheck.backend.commons.entities.mysql.models.commos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.tipos.Unidad;

/**
 * 
 * <b>UnoD</b> <br>
 * Clase que define la propiedad de medida de un elemento en una dimension.
 * 
 * <ul>
 * <li>tamano: Tamaño (número entero)</li>
 * <li>{@link com.reforcheck.backend.commons.entities.mysql.models.tipos.Unidad}</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "uno_d")
public class UnoD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer tamano;
	@Enumerated(EnumType.STRING)
	private Unidad unidad;

	public UnoD() {
		super();
		this.tamano = 0;
		this.unidad = Unidad.SIN_DEFINIR;
	}

	public UnoD(Integer tamano, Unidad unidad) {
		super();
		this.tamano = tamano;
		this.unidad = unidad;
	}

	public Integer getTamano() {
		return tamano;
	}

	public void setTamano(Integer tamano) {
		this.tamano = tamano;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

}
