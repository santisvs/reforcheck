package com.reforcheck.backend.commons.entities.mysql.models.elemento.climatizacion;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.climatizacion.Climatizacion;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

/**
 * 
 * <b>Climatizacion</b> <br>
 * Clase que define el elemento climatizacion.
 * 
 * <ul>
 * <li>bombaCalor: boolean que indica si tiene bomba de calor</li>
 * <li>redConductos: boolean que indica si tiene red de conductos</li>
 * <li>ventilador: boolean que indica si tiene ventilador</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "climatizaciones")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Climatizacion extends Elemento {

	private static final long serialVersionUID = 1L;

	@Column(name = "bomba_calor")
	private Boolean bombaCalor;
	@Column(name = "red_conductos")
	private Boolean redConductos;
	private Boolean ventilador;

	public Climatizacion() {
		super();
		this.bombaCalor = false;
		this.redConductos = false;
		this.ventilador = false;
	}

	public Climatizacion(Boolean bombaCalor, Boolean redConductos, Boolean ventilador, String idElem, String idEstancia,
			Info info, List<String> referenciasFabricantes, List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.bombaCalor = bombaCalor;
		this.redConductos = redConductos;
		this.ventilador = ventilador;
	}

	public Climatizacion(Boolean bombaCalor, Boolean redConductos, Boolean ventilador, Elemento elemento) {
		setElemento(elemento);
		this.bombaCalor = bombaCalor;
		this.redConductos = redConductos;
		this.ventilador = ventilador;
	}
	
	public Climatizacion(Climatizacion climatizacion) {
		setBombaCalor(climatizacion.getBombaCalor());
		setRedConductos(climatizacion.getRedConductos());
		setVentilador(climatizacion.getVentilador());
		setIdElem(climatizacion.getIdElem());
		setIdEstancia(climatizacion.getIdEstancia());
		setInfo(climatizacion.getInfo());
		setReferenciasFabricantes(climatizacion.getReferenciasFabricantes());
		setFabricantes(climatizacion.getFabricantes());
		setPrecio(climatizacion.getPrecio());
	}
	
	public void setClimatizacion(Climatizacion climatizacion) {
		setBombaCalor(climatizacion.getBombaCalor());
		setRedConductos(climatizacion.getRedConductos());
		setVentilador(climatizacion.getVentilador());
		setIdElem(climatizacion.getIdElem());
		setIdEstancia(climatizacion.getIdEstancia());
		setInfo(climatizacion.getInfo());
		setReferenciasFabricantes(climatizacion.getReferenciasFabricantes());
		setFabricantes(climatizacion.getFabricantes());
		setPrecio(climatizacion.getPrecio());
	}

	public Boolean getBombaCalor() {
		return bombaCalor;
	}

	public void setBombaCalor(Boolean bombaCalor) {
		this.bombaCalor = bombaCalor;
	}

	public Boolean getRedConductos() {
		return redConductos;
	}

	public void setRedConductos(Boolean redConductos) {
		this.redConductos = redConductos;
	}

	public Boolean getVentilador() {
		return ventilador;
	}

	public void setVentilador(Boolean ventilador) {
		this.ventilador = ventilador;
	}

}
