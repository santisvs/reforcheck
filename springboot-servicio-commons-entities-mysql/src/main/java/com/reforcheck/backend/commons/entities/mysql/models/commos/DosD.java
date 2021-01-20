package com.reforcheck.backend.commons.entities.mysql.models.commos;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.Unidad;

/**
 * 
 * <b>DosD</b> <br>
 * Clase que define la propiedad de medida de un elemento en dos dimensiones.
 * 
 * <ul>
 * <li>alto: Tamaño (número entero)</li>
 * <li>largo: Tamaño (número entero)</li>
 * <li>{@link com.reforcheck.backend.commons.entities.mysql.models.tipos.Unidad}</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Embeddable
public class DosD {

	private Integer alto;
	private Integer largo;
	@Enumerated(EnumType.STRING)
	private Unidad unidad;

	public DosD() {
		super();
		this.alto = ConstantsTypes.ENT_0;
		this.largo = ConstantsTypes.ENT_0;
		this.unidad = Unidad.SIN_DEFINIR;
	}

	public DosD(Integer alto, Integer largo, Unidad unidad) {
		super();
		this.alto = alto;
		this.largo = largo;
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

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

}
