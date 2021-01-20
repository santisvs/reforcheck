package com.reforcheck.backend.commons.entities.mysql.models.commos;

import javax.persistence.Embeddable;

/**
 * 
 * <b>Info</b> <br>
 * Clase que define la propiedad de información adicional de un elemento.
 * 
 * <ul>
 * <li>notas: Texto de información</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Embeddable
public class Info {

	private String notas;

	public Info() {
		super();
		this.notas = "";
	}

	public Info(String notas) {
		super();
		this.notas = notas;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

}
