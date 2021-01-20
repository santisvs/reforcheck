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
 * <b>Ventana</b> <br>
 * Clase que define el elemento ventana.
 * 
 * <ul>
 * <li>medida: medida 2D que tiene la ventana</li>
 * <li>persiana: boolean que indica si la ventana tiene persiana</li>
 * <li>capialzado: boolean que indica si la ventana tiene capialzado</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "ventanas")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Ventana extends Elemento {

	private static final long serialVersionUID = 1L;

	@Embedded
	private DosD medida;
	private Boolean persiana;
	private Boolean capialzado;

	public Ventana() {
		super();
		this.medida = new DosD();
		this.persiana = false;
		this.capialzado = false;
	}

	public Ventana(DosD medida, Boolean persiana, Boolean capialzado, String idElem, Info info, Fabricante fabricante,
			Precio precio) {
		super(idElem, info, fabricante, precio);
		this.medida = medida;
		this.persiana = persiana;
		this.capialzado = capialzado;
	}

	public Ventana(DosD medida, Boolean persiana, Boolean capialzado, Elemento elemento) {
		setElemento(elemento);
		this.medida = medida;
		this.persiana = persiana;
		this.capialzado = capialzado;
	}

	public DosD getMedida() {
		return medida;
	}

	public void setMedida(DosD medida) {
		this.medida = medida;
	}

	public Boolean getPersiana() {
		return persiana;
	}

	public void setPersiana(Boolean persiana) {
		this.persiana = persiana;
	}

	public Boolean getCapialzado() {
		return capialzado;
	}

	public void setCapialzado(Boolean capialzado) {
		this.capialzado = capialzado;
	}

}
