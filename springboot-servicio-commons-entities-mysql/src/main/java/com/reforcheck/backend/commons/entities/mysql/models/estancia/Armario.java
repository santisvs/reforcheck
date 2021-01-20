package com.reforcheck.backend.commons.entities.mysql.models.estancia;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.entities.mysql.models.commos.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commos.TresD;

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

	public Armario(TresD medida, String idElem, Info info, Fabricante fabricante, Precio precio) {
		super(idElem, info, fabricante, precio);
		this.medida = medida;
	}
	
	public Armario(TresD medida, Elemento elemento) {
		setElemento(elemento);
		this.medida = medida;
	}

	public TresD getMedida() {
		return medida;
	}

	public void setMedida(TresD medida) {
		this.medida = medida;
	}

}
