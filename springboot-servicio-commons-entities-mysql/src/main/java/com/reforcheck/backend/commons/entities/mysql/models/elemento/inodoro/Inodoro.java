package com.reforcheck.backend.commons.entities.mysql.models.elemento.inodoro;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commons.TresD;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.inodoro.Inodoro;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.Material;

/**
 * 
 * <b>Inodoro</b> <br>
 * Clase que define el elemento inodoro.
 * 
 * <ul>
 * <li>suspendido: boolean que indica si esta suspendido</li>
 * <li>demuele: boolean que indica si se demuele</li>
 * <li>ducheta: boolean que indica si tiene ducheta</li>
 * <li>material: material del inodoro</li>
 * <li>medida: medida 3D que tiene el inodoro</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "inodoros")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Inodoro extends Elemento {

	private static final long serialVersionUID = 1L;

	private Boolean suspendido;
	private Boolean demuele;
	private Boolean ducheta;
	@Enumerated(EnumType.STRING)
	private Material material;
	@Embedded
	private TresD medida;

	public Inodoro() {
		super();
		this.suspendido = false;
		this.demuele = false;
		this.ducheta = false;
		this.material = Material.SIN_DEFINIR;
		this.medida = new TresD();
	}

	public Inodoro(Boolean suspendido, Boolean demuele, Boolean ducheta, Material material, TresD medida, String idElem,
			String idEstancia, Info info, List<String> referenciasFabricantes, List<Fabricante> fabricantes,
			Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.suspendido = suspendido;
		this.demuele = demuele;
		this.ducheta = ducheta;
		this.material = material;
		this.medida = medida;
	}

	public Inodoro(Boolean suspendido, Boolean demuele, Boolean ducheta, Material material, TresD medida,
			Elemento elemento) {
		setElemento(elemento);
		this.suspendido = suspendido;
		this.demuele = demuele;
		this.ducheta = ducheta;
		this.material = material;
		this.medida = medida;
	}
	
	public Inodoro(Inodoro inodoro) {
		setSuspendido(inodoro.getSuspendido());
		setDemuele(inodoro.getDemuele());
		setDucheta(inodoro.getDucheta());
		setMaterial(inodoro.getMaterial());
		setMedida(inodoro.getMedida());
		setIdElem(inodoro.getIdElem());
		setIdEstancia(inodoro.getIdEstancia());
		setInfo(inodoro.getInfo());
		setReferenciasFabricantes(inodoro.getReferenciasFabricantes());
		setFabricantes(inodoro.getFabricantes());
		setPrecio(inodoro.getPrecio());
	}
	
	public void setInodoro(Inodoro inodoro) {
		setSuspendido(inodoro.getSuspendido());
		setDemuele(inodoro.getDemuele());
		setDucheta(inodoro.getDucheta());
		setMaterial(inodoro.getMaterial());
		setMedida(inodoro.getMedida());
		setIdElem(inodoro.getIdElem());
		setIdEstancia(inodoro.getIdEstancia());
		setInfo(inodoro.getInfo());
		setReferenciasFabricantes(inodoro.getReferenciasFabricantes());
		setFabricantes(inodoro.getFabricantes());
		setPrecio(inodoro.getPrecio());
	}

	public Boolean getSuspendido() {
		return suspendido;
	}

	public void setSuspendido(Boolean suspendido) {
		this.suspendido = suspendido;
	}

	public Boolean getDemuele() {
		return demuele;
	}

	public void setDemuele(Boolean demuele) {
		this.demuele = demuele;
	}

	public Boolean getDucheta() {
		return ducheta;
	}

	public void setDucheta(Boolean ducheta) {
		this.ducheta = ducheta;
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

}
