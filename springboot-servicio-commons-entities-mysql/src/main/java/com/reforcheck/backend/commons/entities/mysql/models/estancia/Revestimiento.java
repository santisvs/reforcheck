package com.reforcheck.backend.commons.entities.mysql.models.estancia;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commos.DosD;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;

/**
 * 
 * <b>Revestimiento</b> <br>
 * Clase que define el elemento revestimiento.
 * 
 * <ul>
 * <li>medida: medida 2D que tiene el revestimiento</li>
 * <li>ceramico: boolean que indica si el revestimiento es cerámico</li>
 * <li>friso: boolean que indica si el revestimiento es friso</li>
 * <li>panelado: boolean que indica si el revestimiento es panelado</li>
 * <li>moldura: boolean que indica si el revestimiento tiene moldura</li>
 * <li>demuele: boolean que indica si el revestimiento se demuele</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "revestimientos")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Revestimiento extends Elemento {

	private static final long serialVersionUID = 1L;

	@Embedded
	private DosD medida;
	private Boolean ceramico;
	private Boolean friso;
	private Boolean panelado;
	private Boolean moldura;
	private Boolean demuele;

	public Revestimiento() {
		super();
		this.medida = new DosD();
		this.ceramico = false;
		this.friso = false;
		this.panelado = false;
		this.moldura = false;
		this.demuele = false;
	}

	public Revestimiento(DosD medida, Boolean ceramico, Boolean friso, Boolean panelado, Boolean moldura,
			Boolean demuele, String idElem, Info info, Fabricante fabricante, Precio precio) {
		super(idElem, info, fabricante, precio);
		this.medida = medida;
		this.ceramico = ceramico;
		this.friso = friso;
		this.panelado = panelado;
		this.moldura = moldura;
		this.demuele = demuele;
	}

	public Revestimiento(DosD medida, Boolean ceramico, Boolean friso, Boolean panelado, Boolean moldura,
			Boolean demuele, Elemento elemento) {
		setElemento(elemento);
		this.medida = medida;
		this.ceramico = ceramico;
		this.friso = friso;
		this.panelado = panelado;
		this.moldura = moldura;
		this.demuele = demuele;
	}

	public DosD getMedida() {
		return medida;
	}

	public void setMedida(DosD medida) {
		this.medida = medida;
	}

	public Boolean getCeramico() {
		return ceramico;
	}

	public void setCeramico(Boolean ceramico) {
		this.ceramico = ceramico;
	}

	public Boolean getFriso() {
		return friso;
	}

	public void setFriso(Boolean friso) {
		this.friso = friso;
	}

	public Boolean getPanelado() {
		return panelado;
	}

	public void setPanelado(Boolean panelado) {
		this.panelado = panelado;
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
