package com.reforcheck.backend.commons.entities.mysql.models.estancia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commos.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;

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

	public Techo(Boolean falsoTecho, Boolean moldura, Boolean demuele, String idElem, Info info, Fabricante fabricante,
			Precio precio) {
		super(idElem, info, fabricante, precio);
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
