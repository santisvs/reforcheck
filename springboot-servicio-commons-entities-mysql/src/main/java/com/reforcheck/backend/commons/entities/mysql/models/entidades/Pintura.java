package com.reforcheck.backend.commons.entities.mysql.models.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.Cantidad;

/**
 * 
 * <b>Pintura</b> <br>
 * Clase que define el elemento pintura.
 * 
 * <ul>
 * <li>fisuracion:
 * {@link com.reforcheck.backend.commons.entities.mysql.models.tipos}</li>
 * <li>papelPintado: boolean que indica si hay papel pintado</li>
 * <li>gotelet: boolean que indica si hay gotelet</li>
 * <li>raspado: boolean que indica si hay raspado</li>
 * <li>color: nombre o pantone del color</li>
 * <li>acabado: nombre del acabado</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "pinturas")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Pintura extends Elemento {

	private static final long serialVersionUID = 1L;

	private Cantidad fisuracion;
	@Column(name = "papel_pintado")
	private Boolean papelPintado;
	private Boolean gotelet;
	private Boolean raspado;
	private String color;
	private String acabado;

	public Pintura() {
		super();
		this.fisuracion = Cantidad.SIN_DEFINIR;
		this.papelPintado = false;
		this.gotelet = false;
		this.raspado = false;
		this.color = ConstantsTypes.STRING_EMPTY;
		this.acabado = ConstantsTypes.STRING_EMPTY;
	}

	public Pintura(Cantidad fisuracion, Boolean papelPintado, Boolean gotelet, Boolean raspado, String color,
			String acabado, String idElem, String idEstancia, Info info, List<String> referenciasFabricantes,
			List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.fisuracion = fisuracion;
		this.papelPintado = papelPintado;
		this.gotelet = gotelet;
		this.raspado = raspado;
		this.color = color;
		this.acabado = acabado;
	}

	public Pintura(Cantidad fisuracion, Boolean papelPintado, Boolean gotelet, Boolean raspado, String color,
			String acabado, Elemento elemento) {
		setElemento(elemento);
		this.fisuracion = fisuracion;
		this.papelPintado = papelPintado;
		this.gotelet = gotelet;
		this.raspado = raspado;
		this.color = color;
		this.acabado = acabado;
	}

	public Cantidad getFisuracion() {
		return fisuracion;
	}

	public void setFisuracion(Cantidad fisuracion) {
		this.fisuracion = fisuracion;
	}

	public Boolean getPapelPintado() {
		return papelPintado;
	}

	public void setPapelPintado(Boolean papelPintado) {
		this.papelPintado = papelPintado;
	}

	public Boolean getGotelet() {
		return gotelet;
	}

	public void setGotelet(Boolean gotelet) {
		this.gotelet = gotelet;
	}

	public Boolean getRaspado() {
		return raspado;
	}

	public void setRaspado(Boolean raspado) {
		this.raspado = raspado;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAcabado() {
		return acabado;
	}

	public void setAcabado(String acabado) {
		this.acabado = acabado;
	}

}
