package com.reforcheck.backend.commons.entities.postgresql.models;

import java.io.Serializable;

public class Worker extends UserApp implements Serializable {

	private static final long serialVersionUID = 6328512904559087750L;

	private TypeWorker tipo;
	private Double produccionMedia;
	private Double salarioHora;
	private String licencia;

	public TypeWorker getTipo() {
		return tipo;
	}

	public void setTipo(TypeWorker tipo) {
		this.tipo = tipo;
	}

	public Double getProduccionMedia() {
		return produccionMedia;
	}

	public void setProduccionMedia(Double produccionMedia) {
		this.produccionMedia = produccionMedia;
	}

	public Double getSalarioHora() {
		return salarioHora;
	}

	public void setSalarioHora(Double salarioHora) {
		this.salarioHora = salarioHora;
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

}
