package com.reforcheck.backend.commons.entities.mysql.models.elemento.iluminacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.common.unod.UnoD;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

/**
 * 
 * <b>Iluminacion</b> <br>
 * Clase que define el elemento iluminacion.
 * 
 * <ul>
 * <li>empotrada: boolean que indica si esta empotrada</li>
 * <li>focos: número de focos</li>
 * <li>downLights: número de downLights</li>
 * <li>tramos: tramos de iluminación</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "iluminaciones")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Iluminacion extends Elemento {

	private static final long serialVersionUID = 1L;

	private Boolean empotrada;
	private Integer focos;
	@Column(name = "down_lights")
	private Integer downLights;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UnoD.class)
	private List<UnoD> tramos;

	public Iluminacion() {
		super();
		this.empotrada = false;
		this.focos = ConstantsTypes.ENT_0;
		this.downLights = ConstantsTypes.ENT_0;
		this.tramos = new ArrayList<UnoD>();
	}

	public Iluminacion(Boolean empotrada, Integer focos, Integer downLights, ArrayList<UnoD> tramos, String idElem,
			String idEstancia, Info info, List<String> referenciasFabricantes, List<Fabricante> fabricantes,
			Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.empotrada = empotrada;
		this.focos = focos;
		this.downLights = downLights;
		this.tramos = tramos;
	}

	public Iluminacion(Boolean empotrada, Integer focos, Integer downLights, ArrayList<UnoD> tramos,
			Elemento elemento) {
		setElemento(elemento);
		this.empotrada = empotrada;
		this.focos = focos;
		this.downLights = downLights;
		this.tramos = tramos;
	}

	public Boolean getEmpotrada() {
		return empotrada;
	}

	public void setEmpotrada(Boolean empotrada) {
		this.empotrada = empotrada;
	}

	public Integer getFocos() {
		return focos;
	}

	public void setFocos(Integer focos) {
		this.focos = focos;
	}

	public Integer getDownLights() {
		return downLights;
	}

	public void setDownLights(Integer downLights) {
		this.downLights = downLights;
	}

	public List<UnoD> getTramos() {
		return tramos;
	}

	public void setTramos(List<UnoD> tramos) {
		this.tramos = tramos;
	}

}
