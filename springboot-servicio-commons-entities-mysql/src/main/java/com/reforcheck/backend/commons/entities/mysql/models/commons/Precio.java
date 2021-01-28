package com.reforcheck.backend.commons.entities.mysql.models.commons;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.Divisa;


/**
 * 
 * <b>Precio</b> <br>
 * Clase que define el precio.
 * 
 * <ul>
 * <li>valor: Coste</li>
 * <li>divisa: Moneda</li>
 * <li>iva: Impuesto</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Embeddable
public class Precio {

	private Double valor;
	@Enumerated(EnumType.STRING)
	private Divisa divisa;
	private Double iva;

	public Precio() {
		super();
		this.valor = ConstantsTypes.DOUBLE_0;
		this.divisa = Divisa.SIN_DEFINIR;
		this.iva = ConstantsTypes.DOUBLE_0;
	}

	public Precio(Double valor, Divisa divisa, Double iva) {
		super();
		this.valor = valor;
		this.divisa = divisa;
		this.iva = iva;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

}
