package com.reforcheck.backend.commons.entities.mysql.models.estancia;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commos.DosD;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;

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
@Table(name = "rodapies")
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

	public Solado(String tipo, DosD medida, String idElem, Info info, Fabricante fabricante, Precio precio) {
		super(idElem, info, fabricante, precio);
		this.tipo = tipo;
		this.medida = medida;
	}

	public Solado(String tipo, DosD medida, Elemento elemento) {
		setElemento(elemento);
		this.tipo = tipo;
		this.medida = medida;
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
