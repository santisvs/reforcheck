package com.reforcheck.backend.commons.entities.mysql.models.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

/**
 * 
 * <b>Techo</b> <br>
 * Clase que define el elemento techo.
 * 
 * <ul>
 * <li>falsoTecho: boolean que indica si el techo tiene falso techo</li>
 * <li>moldura: boolean que indica si el techo tiene moldura</li>
 * <li>demuele: boolean que indica si el techo se demuele</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "techos")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Techo extends Elemento {

	private static final long serialVersionUID = 1L;

	@Column(name = "falso_techo")
	private Boolean falsoTecho;
	private Boolean moldura;
	private Boolean demuele;

	public Techo() {
		super();
		this.falsoTecho = false;
		this.moldura = false;
		this.demuele = false;
	}

	public Techo(Boolean falsoTecho, Boolean moldura, Boolean demuele, String idElem, String idEstancia, Info info,
			List<String> referenciasFabricantes, List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.falsoTecho = falsoTecho;
		this.moldura = moldura;
		this.demuele = demuele;
	}

	public Techo(Boolean falsoTecho, Boolean moldura, Boolean demuele, Elemento elemento) {
		setElemento(elemento);
		this.falsoTecho = falsoTecho;
		this.moldura = moldura;
		this.demuele = demuele;
	}

	public Boolean getFalsoTecho() {
		return falsoTecho;
	}

	public void setFalsoTecho(Boolean falsoTecho) {
		this.falsoTecho = falsoTecho;
	}

	public Boolean getMoldura() {
		return moldura;
	}

	public void setMoldura(Boolean moldura) {
		this.moldura = moldura;
	}

	public Boolean getDemuele() {
		return demuele;
	}

	public void setDemuele(Boolean demuele) {
		this.demuele = demuele;
	}

}
