package com.reforcheck.backend.commons.entities.mysql.models.estancia;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commos.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commos.TresD;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.Material;

/**
 * 
 * <b>Bidet</b> <br>
 * Clase que define el elemento bañera.
 * 
 * <ul>
 * <li>suspendido: boolean que indica si esta suspendido</li>
 * <li>demuele: boolean que indica si se demuele</li>
 * <li>material: material del bidet</li>
 * <li>medida: medida 3D que tiene el bidet</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "bidets")
@PrimaryKeyJoinColumn(name="elementoId")
public class Bidet extends Elemento {

	private static final long serialVersionUID = 1L;
	
	private Boolean suspendido;
	private Boolean demuele;
	@Enumerated(EnumType.STRING)
	private Material material;
	@Embedded
	private TresD medida;

	public Bidet() {
		super();
		this.suspendido = false;
		this.demuele = false;
		this.material = Material.SIN_DEFINIR;
		this.medida = new TresD();
	}

	public Bidet(Boolean suspendido, Boolean demuele, Material material, TresD medida, String idElem, Info info,
			Fabricante fabricante, Precio precio) {
		super(idElem, info, fabricante, precio);
		this.suspendido = suspendido;
		this.demuele = demuele;
		this.material = material;
		this.medida = medida;
	}
	
	public Bidet(Boolean suspendido, Boolean demuele, Material material, TresD medida, Elemento elemento) {
		setElemento(elemento);
		this.suspendido = suspendido;
		this.demuele = demuele;
		this.material = material;
		this.medida = medida;
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
