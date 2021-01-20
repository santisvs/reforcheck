package com.reforcheck.backend.commons.entities.mysql.models.estancia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commos.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commos.TresD;
import com.reforcheck.backend.commons.entities.mysql.models.commos.UnoD;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.Material;

/**
 * 
 * <b>Banera</b> <br>
 * Clase que define el elemento bañera.
 * 
 * <ul>
 * <li>demuele: boolean que indica si se demuele</li>
 * <li>material: material de la bañera</li>
 * <li>medida: medida 3D que tiene la bañera</li>
 * <li>griferiaEmpotrada: boolean que indica si tiene griferia empotrada</li>
 * <li>mampara: boolean que indica si tiene mampara</li>
 * <li>recta: boolean que indica si la bañera es recta</li>
 * <li>tramos: si la bañera no es recta, se habilita la información de los
 * tramos de la bañera</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "baneras")
@PrimaryKeyJoinColumn(name="elementoId")
public class Banera extends Elemento {
	
	private static final long serialVersionUID = 1L;

	private Boolean demuele;
	@Enumerated(EnumType.STRING)
	private Material material;
	@Embedded
	private TresD medida;
	@Column(name = "griferia_empotrada")
	private Boolean griferiaEmpotrada;
	private Boolean mampara;
	private Boolean recta;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UnoD> tramos;

	public Banera() {
		super();
		this.demuele = false;
		this.material = Material.SIN_DEFINIR;
		this.medida = new TresD();
		this.griferiaEmpotrada = false;
		this.mampara = false;
		this.recta = false;
		this.tramos = new ArrayList<UnoD>();
	}

	public Banera(Boolean demuele, Material material, TresD medida, Boolean griferiaEmpotrada, Boolean mampara,
			Boolean recta, ArrayList<UnoD> tramos, String idElem, Info info, Fabricante fabricante, Precio precio) {
		super(idElem, info, fabricante, precio);
		this.demuele = demuele;
		this.material = material;
		this.medida = medida;
		this.griferiaEmpotrada = griferiaEmpotrada;
		this.mampara = mampara;
		this.recta = recta;
		this.tramos = tramos;
	}
	
	public Banera(Boolean demuele, Material material, TresD medida, Boolean griferiaEmpotrada, Boolean mampara,
			Boolean recta, ArrayList<UnoD> tramos, Elemento elemento) {
		setElemento(elemento);
		this.demuele = demuele;
		this.material = material;
		this.medida = medida;
		this.griferiaEmpotrada = griferiaEmpotrada;
		this.mampara = mampara;
		this.recta = recta;
		this.tramos = tramos;
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

	public Boolean getGriferiaEmpotrada() {
		return griferiaEmpotrada;
	}

	public void setGriferiaEmpotrada(Boolean griferiaEmpotrada) {
		this.griferiaEmpotrada = griferiaEmpotrada;
	}

	public Boolean getMampara() {
		return mampara;
	}

	public void setMampara(Boolean mampara) {
		this.mampara = mampara;
	}

	public Boolean getRecta() {
		return recta;
	}

	public void setRecta(Boolean recta) {
		if (recta) {
			this.tramos = null;
		} else {
			this.tramos = new ArrayList<UnoD>();
		}
		this.recta = recta;
	}

	public List<UnoD> getTramos() {
		return tramos;
	}

	public void setTramos(ArrayList<UnoD> tramos) {
		this.tramos = tramos;
	}
	
	

}
