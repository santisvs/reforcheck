package com.reforcheck.backend.commons.entities.mysql.models.elemento.armario;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commons.TresD;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

/**
 * 
 * <b>Armario</b> <br>
 * Clase que define el elemento armario.
 * 
 * <ul>
 * <li>medida: medida 3D que tiene el armario</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "armarios")
@PrimaryKeyJoinColumn(name="elementoId")
public class Armario extends Elemento  {

	private static final long serialVersionUID = 1L;
	
	@Embedded
	private TresD medida;

	public Armario() {
		super();
		this.medida = new TresD();
	}

	public Armario(TresD medida, String idElem, String idEstancia, Info info, List<String> referenciasFabricantes, List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.medida = medida;
	}
	
	public Armario(TresD medida, Elemento elemento) {
		setElemento(elemento);
		this.medida = medida;
	}
	
	public Armario(Armario armario) {
		setMedida(armario.medida);
		setIdElem(armario.getIdElem());
		setIdEstancia(armario.getIdEstancia());
		setInfo(armario.getInfo());
		setReferenciasFabricantes(armario.getReferenciasFabricantes());
		setFabricantes(armario.getFabricantes());
		setPrecio(armario.getPrecio());
	}
	
	public void setArmario(Armario armario) {
		setMedida(armario.medida);
		setIdElem(armario.getIdElem());
		setIdEstancia(armario.getIdEstancia());
		setInfo(armario.getInfo());
		setReferenciasFabricantes(armario.getReferenciasFabricantes());
		setFabricantes(armario.getFabricantes());
		setPrecio(armario.getPrecio());
	}

	public TresD getMedida() {
		return medida;
	}

	public void setMedida(TresD medida) {
		this.medida = medida;
	}

}
