package com.reforcheck.backend.commons.entities.mysql.models.elemento.mobiliarioobra;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;
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

	public MobiliarioObra(Integer cantidad, Unidad unidad, Boolean desmontable, String idElem, String idEstancia,
			Info info, List<String> referenciasFabricantes, List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
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
	
	public MobiliarioObra(MobiliarioObra mobiliarioObra) {
		setCantidad(mobiliarioObra.getCantidad());
		setUnidad(mobiliarioObra.getUnidad());
		setDesmontable(mobiliarioObra.getDesmontable());
		setIdElem(mobiliarioObra.getIdElem());
		setIdEstancia(mobiliarioObra.getIdEstancia());
		setInfo(mobiliarioObra.getInfo());
		setReferenciasFabricantes(mobiliarioObra.getReferenciasFabricantes());
		setFabricantes(mobiliarioObra.getFabricantes());
		setPrecio(mobiliarioObra.getPrecio());
	}
	
	public void setMobiliarioObra(MobiliarioObra mobiliarioObra) {
		setCantidad(mobiliarioObra.getCantidad());
		setUnidad(mobiliarioObra.getUnidad());
		setDesmontable(mobiliarioObra.getDesmontable());
		setIdElem(mobiliarioObra.getIdElem());
		setIdEstancia(mobiliarioObra.getIdEstancia());
		setInfo(mobiliarioObra.getInfo());
		setReferenciasFabricantes(mobiliarioObra.getReferenciasFabricantes());
		setFabricantes(mobiliarioObra.getFabricantes());
		setPrecio(mobiliarioObra.getPrecio());
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
