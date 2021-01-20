package com.reforcheck.backend.commons.entities.mysql.models.commos;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.reforcheck.backend.commons.entities.mysql.models.tipos.Unidad;

/**
 * 
 * <b>TresD</b> <br>
 * Clase que define la propiedad de medida de un elemento en tres dimensiones.
 * 
 * <ul>
 * <li>alto: Tamaño (número entero)</li>
 * <li>largo: Tamaño (número entero)</li>
 * <li>ancho: Tamaño (número entero)</li>
 * <li>{@link com.reforcheck.backend.commons.entities.mysql.models.tipos.Unidad}</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Embeddable
public class TresD {
	
	private Integer alto;
	private Integer largo;
	private Integer ancho;
	@Enumerated(EnumType.STRING)
	private Unidad unidad;

	public TresD() {
		super();
		this.alto = 0;
		this.largo = 0;
		this.ancho = 0;
		this.unidad = Unidad.SIN_DEFINIR;
	}

	public TresD(Integer alto, Integer largo, Integer ancho, Unidad unidad) {
		super();
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
		this.unidad = unidad;
	}

	public Integer getAlto() {
		return alto;
	}

	public void setAlto(Integer alto) {
		this.alto = alto;
	}

	public Integer getLargo() {
		return largo;
	}

	public void setLargo(Integer largo) {
		this.largo = largo;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

}
