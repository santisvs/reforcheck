package com.reforcheck.backend.commons.entities.mysql.models.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commons.TresD;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.Material;

/**
 * 
 * <b>Radiador</b> <br>
 * Clase que define el elemento radiador.
 * 
 * <ul>
 * <li>energia: nombre de la energia que utiliza</li>
 * <li>material: material de la bañera</li>
 * <li>medida: medida 3D que tiene la bañera</li>
 * <li>numElementos: número de módulos</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "radiadores")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Radiador extends Elemento {

	private static final long serialVersionUID = 1L;

	private String energia;
	@Enumerated(EnumType.STRING)
	private Material material;
	@Embedded
	private TresD medida;
	@Column(name = "num_elementos")
	private Integer numElementos;

	public Radiador() {
		super();
		this.energia = ConstantsTypes.STRING_EMPTY;
		this.material = Material.SIN_DEFINIR;
		this.medida = new TresD();
		this.numElementos = ConstantsTypes.ENT_0;
	}

	public Radiador(String energia, Material material, TresD medida, Integer numElementos, String idElem,
			String idEstancia, Info info, List<String> referenciasFabricantes, List<Fabricante> fabricantes,
			Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.energia = energia;
		this.material = material;
		this.medida = medida;
		this.numElementos = numElementos;
	}

	public Radiador(String energia, Material material, TresD medida, Integer numElementos, Elemento elemento) {
		setElemento(elemento);
		this.energia = energia;
		this.material = material;
		this.medida = medida;
		this.numElementos = numElementos;
	}

	public String getEnergia() {
		return energia;
	}

	public void setEnergia(String energia) {
		this.energia = energia;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public TresD getMedida() {
		return medida;
	}

	public void setMedida(TresD medida) {
		this.medida = medida;
	}

	public Integer getNumElementos() {
		return numElementos;
	}

	public void setNumElementos(Integer numElementos) {
		this.numElementos = numElementos;
	}

}
