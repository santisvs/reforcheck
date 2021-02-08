package com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo;

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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo.Lavabo;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.Material;

/**
 * 
 * <b>Lavabo</b> <br>
 * Clase que define el elemento lavabo.
 * 
 * <ul>
 * <li>senos: número de senos</li>
 * <li>demuele: boolean que indica si se demuele</li>
 * <li>mueble: boolean que indica si tiene mueble</li>
 * <li>material: material de la bañera</li>
 * <li>medida: medida 3D que tiene la bañera</li>
 * <li>griferiaEmpotrada: boolean que indica si tiene griferia empotrada</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "lavabos")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Lavabo extends Elemento {

	private static final long serialVersionUID = 1L;

	private Integer senos;
	private Boolean demuele;
	private Boolean mueble;
	@Enumerated(EnumType.STRING)
	private Material material;
	@Embedded
	private TresD medida;
	@Column(name = "griferia_empotrada")
	private Boolean griferiaEmpotrada;

	public Lavabo() {
		super();
		this.senos = ConstantsTypes.ENT_0;
		this.demuele = false;
		this.mueble = false;
		this.material = Material.SIN_DEFINIR;
		this.medida = new TresD();
		this.griferiaEmpotrada = false;
	}

	public Lavabo(Integer senos, Boolean demuele, Boolean mueble, Material material, TresD medida,
			Boolean griferiaEmpotrada, String idElem, String idEstancia, Info info, List<String> referenciasFabricantes,
			List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.senos = senos;
		this.demuele = demuele;
		this.mueble = mueble;
		this.material = material;
		this.medida = medida;
		this.griferiaEmpotrada = griferiaEmpotrada;
	}

	public Lavabo(Integer senos, Boolean demuele, Boolean mueble, Material material, TresD medida,
			Boolean griferiaEmpotrada, Elemento elemento) {
		setElemento(elemento);
		this.senos = senos;
		this.demuele = demuele;
		this.mueble = mueble;
		this.material = material;
		this.medida = medida;
		this.griferiaEmpotrada = griferiaEmpotrada;
	}
	
	public Lavabo(Lavabo lavabo) {
		setSenos(lavabo.getSenos());
		setDemuele(lavabo.getDemuele());
		setMueble(lavabo.getMueble());
		setMaterial(lavabo.getMaterial());
		setMedida(lavabo.getMedida());
		setGriferiaEmpotrada(lavabo.getGriferiaEmpotrada());
		setIdElem(lavabo.getIdElem());
		setIdEstancia(lavabo.getIdEstancia());
		setInfo(lavabo.getInfo());
		setReferenciasFabricantes(lavabo.getReferenciasFabricantes());
		setFabricantes(lavabo.getFabricantes());
		setPrecio(lavabo.getPrecio());
	}
	
	public void setLavabo(Lavabo lavabo) {
		setSenos(lavabo.getSenos());
		setDemuele(lavabo.getDemuele());
		setMueble(lavabo.getMueble());
		setMaterial(lavabo.getMaterial());
		setMedida(lavabo.getMedida());
		setGriferiaEmpotrada(lavabo.getGriferiaEmpotrada());
		setIdElem(lavabo.getIdElem());
		setIdEstancia(lavabo.getIdEstancia());
		setInfo(lavabo.getInfo());
		setReferenciasFabricantes(lavabo.getReferenciasFabricantes());
		setFabricantes(lavabo.getFabricantes());
		setPrecio(lavabo.getPrecio());
	}

	public Integer getSenos() {
		return senos;
	}

	public void setSenos(Integer senos) {
		this.senos = senos;
	}

	public Boolean getDemuele() {
		return demuele;
	}

	public void setDemuele(Boolean demuele) {
		this.demuele = demuele;
	}

	public Boolean getMueble() {
		return mueble;
	}

	public void setMueble(Boolean mueble) {
		this.mueble = mueble;
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

	public Boolean getGriferiaEmpotrada() {
		return griferiaEmpotrada;
	}

	public void setGriferiaEmpotrada(Boolean griferiaEmpotrada) {
		this.griferiaEmpotrada = griferiaEmpotrada;
	}

}
