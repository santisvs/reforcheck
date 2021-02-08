package com.reforcheck.backend.commons.entities.mysql.models.elemento.solado;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commons.DosD;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.solado.Solado;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

/**
 * 
 * <b>Solado</b> <br>
 * Clase que define el elemento solado.
 * 
 * <ul>
 * <li>tipo: nombre del tipo de solado</li>
 * <li>medida: medida 2D que tiene el solado</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "solados")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Solado extends Elemento {

	private static final long serialVersionUID = 1L;

	private String tipo;
	@Embedded
	private DosD medida;

	public Solado() {
		super();
		this.tipo = ConstantsTypes.STRING_EMPTY;
		this.medida = new DosD();
	}

	public Solado(String tipo, DosD medida, String idElem, String idEstancia, Info info, List<String> referenciasFabricantes, List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.tipo = tipo;
		this.medida = medida;
	}

	public Solado(String tipo, DosD medida, Elemento elemento) {
		setElemento(elemento);
		this.tipo = tipo;
		this.medida = medida;
	}
	
	public Solado(Solado solado) {
		setTipo(solado.getTipo());
		setMedida(solado.getMedida());
		setIdElem(solado.getIdElem());
		setIdEstancia(solado.getIdEstancia());
		setInfo(solado.getInfo());
		setReferenciasFabricantes(solado.getReferenciasFabricantes());
		setFabricantes(solado.getFabricantes());
		setPrecio(solado.getPrecio());
	}
	
	public void setSolado(Solado solado) {
		setTipo(solado.getTipo());
		setMedida(solado.getMedida());
		setIdElem(solado.getIdElem());
		setIdEstancia(solado.getIdEstancia());
		setInfo(solado.getInfo());
		setReferenciasFabricantes(solado.getReferenciasFabricantes());
		setFabricantes(solado.getFabricantes());
		setPrecio(solado.getPrecio());
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public DosD getMedida() {
		return medida;
	}

	public void setMedida(DosD medida) {
		this.medida = medida;
	}

}
