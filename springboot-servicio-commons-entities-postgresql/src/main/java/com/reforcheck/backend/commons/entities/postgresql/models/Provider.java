package com.reforcheck.backend.commons.entities.postgresql.models;

import java.io.Serializable;

public class Provider extends UserApp implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cif;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}
	
	
}
