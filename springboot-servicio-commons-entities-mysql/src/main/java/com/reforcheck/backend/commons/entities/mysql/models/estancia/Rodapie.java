package com.reforcheck.backend.commons.entities.mysql.models.estancia;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commos.UnoD;

/**
 * 
 * <b>Rodapie</b> <br>
 * Clase que define el elemento rodapie.
 * 
 * <ul>
 * <li>tipo: nombre del tipo de rodapie</li>
 * <li>alto: medida 1D que tiene el rodapie</li>
 * <li>largo: medida 1D que tiene el rodapie</li>
 * <li>ancho: medida 1D que tiene el rodapie</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "rodapies")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Rodapie extends Elemento {

	private static final long serialVersionUID = 1L;

	private String tipo;
	@Embedded
	private UnoD alto;
	@Embedded
	private UnoD largo;
	@Embedded
	private UnoD ancho;

	public Rodapie() {
		super();
		this.tipo = ConstantsTypes.STRING_EMPTY;
		this.alto = new UnoD();
		this.largo = new UnoD();
		this.ancho = new UnoD();
	}

	public Rodapie(String tipo, UnoD alto, UnoD largo, UnoD ancho, String idElem, Info info, Fabricante fabricante,
			Precio precio) {
		super(idElem, info, fabricante, precio);
		this.tipo = tipo;
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
	}

	public Rodapie(String tipo, UnoD alto, UnoD largo, UnoD ancho, Elemento elemento) {
		setElemento(elemento);
		this.tipo = tipo;
		this.alto = alto;
		this.largo = largo;
		this.ancho = ancho;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public UnoD getAlto() {
		return alto;
	}

	public void setAlto(UnoD alto) {
		this.alto = alto;
	}

	public UnoD getLargo() {
		return largo;
	}

	public void setLargo(UnoD largo) {
		this.largo = largo;
	}

	public UnoD getAncho() {
		return ancho;
	}

	public void setAncho(UnoD ancho) {
		this.ancho = ancho;
	}

}