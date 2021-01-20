package com.reforcheck.backend.commons.entities.mysql.models.estancia;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.Unidad;

/**
 * 
 * <b>Banera</b> <br>
 * Clase que define el elemento bañera.
 * 
 * <ul>
 * <li>cantidad: número de muebles de obra</li>
 * <li>unidad: unidad de medida</li>
 * <li>desmontable: boolean que indica si son desmontables</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "mobiliario_obras")
@PrimaryKeyJoinColumn(name = "elementoId")
public class MobiliarioObra extends Elemento {

	private static final long serialVersionUID = 1L;

	private Integer cantidad;
	@Enumerated(EnumType.STRING)
	private Unidad unidad;
	private Boolean desmontable;

	public MobiliarioObra() {
		super();
		this.cantidad = ConstantsTypes.ENT_0;
		this.unidad = Unidad.SIN_DEFINIR;
		this.desmontable = false;
	}

	public MobiliarioObra(Integer cantidad, Unidad unidad, Boolean desmontable, String idElem, Info info,
			Fabricante fabricante, Precio precio) {
		super(idElem, info, fabricante, precio);
		this.cantidad = cantidad;
		this.unidad = unidad;
		this.desmontable = desmontable;
	}

	public MobiliarioObra(Integer cantidad, Unidad unidad, Boolean desmontable, Elemento elemento) {
		setElemento(elemento);
		this.cantidad = cantidad;
		this.unidad = unidad;
		this.desmontable = desmontable;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Boolean getDesmontable() {
		return desmontable;
	}

	public void setDesmontable(Boolean desmontable) {
		this.desmontable = desmontable;
	}

}
