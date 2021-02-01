package com.reforcheck.backend.commons.entities.mysql.models.elemento.puerta;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commons.TresD;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

/**
 * 
 * <b>Puerta</b> <br>
 * Clase que define el elemento puerta.
 * 
 * <ul>
 * <li>medida: medida 3D que tiene la bañera</li>
 * <li>estandar: boolean que indica si es estandar</li>
 * <li>espesorTabique: medida 1D de espesor del tabique</li>
 * <li>mano: boolean que indica la mano (Izquierda true, Derecha false)</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "puertas")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Puerta extends Elemento {

	private static final long serialVersionUID = 1L;

	@Embedded
	private TresD medida;
	private Boolean estandar;
	private Boolean mano;

	public Puerta() {
		super();
		this.medida = new TresD();
		this.estandar = false;
		this.mano = false;
	}

	public Puerta(TresD medida, Boolean estandar, Boolean mano, String idElem, String idEstancia,
			Info info, List<String> referenciasFabricantes, List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.medida = medida;
		this.estandar = estandar;
		this.mano = mano;
	}

	public Puerta(TresD medida, Boolean estandar, Boolean mano, Elemento elemento) {
		setElemento(elemento);
		this.medida = medida;
		this.estandar = estandar;
		this.mano = mano;
	}
	
	public Puerta(Puerta puerta) {
		setMedida(puerta.getMedida());
		setEstandar(puerta.getEstandar());
		setMano(puerta.getMano());
		setIdElem(puerta.getIdElem());
		setIdEstancia(puerta.getIdEstancia());
		setInfo(puerta.getInfo());
		setReferenciasFabricantes(puerta.getReferenciasFabricantes());
		setFabricantes(puerta.getFabricantes());
		setPrecio(puerta.getPrecio());
	}
	
	public void setPuerta(Puerta puerta) {
		setMedida(puerta.getMedida());
		setEstandar(puerta.getEstandar());
		setMano(puerta.getMano());
		setIdElem(puerta.getIdElem());
		setIdEstancia(puerta.getIdEstancia());
		setInfo(puerta.getInfo());
		setReferenciasFabricantes(puerta.getReferenciasFabricantes());
		setFabricantes(puerta.getFabricantes());
		setPrecio(puerta.getPrecio());
	}

	public TresD getMedida() {
		return medida;
	}

	public void setMedida(TresD medida) {
		this.medida = medida;
	}

	public Boolean getEstandar() {
		return estandar;
	}

	public void setEstandar(Boolean estandar) {
		this.estandar = estandar;
	}

	public Boolean getMano() {
		return mano;
	}

	public void setMano(Boolean mano) {
		this.mano = mano;
	}

}
