package com.reforcheck.backend.commons.entities.mysql.models.elemento.rodapie;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.common.unod.UnoD;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.rodapie.Rodapie;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

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
	@OneToOne(cascade = CascadeType.ALL, targetEntity = UnoD.class)
	private UnoD alto;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = UnoD.class)
	private UnoD largo;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = UnoD.class)
	private UnoD ancho;

	public Rodapie() {
		super();
		this.tipo = ConstantsTypes.STRING_EMPTY;
		this.alto = new UnoD();
		this.largo = new UnoD();
		this.ancho = new UnoD();
	}

	public Rodapie(String tipo, UnoD alto, UnoD largo, UnoD ancho, String idElem, String idEstancia, Info info, List<String> referenciasFabricantes, List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
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
	
	public Rodapie(Rodapie rodapie) {
		setTipo(rodapie.getTipo());
		setAlto(rodapie.getAlto());
		setLargo(rodapie.getLargo());
		setAncho(rodapie.getAncho());
		setIdElem(rodapie.getIdElem());
		setIdEstancia(rodapie.getIdEstancia());
		setInfo(rodapie.getInfo());
		setReferenciasFabricantes(rodapie.getReferenciasFabricantes());
		setFabricantes(rodapie.getFabricantes());
		setPrecio(rodapie.getPrecio());
	}
	
	public void setRodapie(Rodapie rodapie) {
		setTipo(rodapie.getTipo());
		setAlto(rodapie.getAlto());
		setLargo(rodapie.getLargo());
		setAncho(rodapie.getAncho());
		setIdElem(rodapie.getIdElem());
		setIdEstancia(rodapie.getIdEstancia());
		setInfo(rodapie.getInfo());
		setReferenciasFabricantes(rodapie.getReferenciasFabricantes());
		setFabricantes(rodapie.getFabricantes());
		setPrecio(rodapie.getPrecio());
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
