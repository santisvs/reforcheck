package com.reforcheck.backend.commons.entities.postgresql.models;

import java.io.Serializable;

import com.reforcheck.backend.commons.constants.ConstantsTypes;

public class TypeWorker implements Serializable {

	private static final long serialVersionUID = 6179344909450190442L;

	private String name;
	private Double averageProduction;
	private Double priceHour;

	public TypeWorker() {
		super();
		this.name=ConstantsTypes.STRING_EMPTY;
		this.averageProduction=ConstantsTypes.DOUBLE_0;
		this.priceHour=ConstantsTypes.DOUBLE_0;
	}

	public TypeWorker(String name, Double averageProduction, Double priceHour) {
		super();
		this.name = name;
		this.averageProduction = averageProduction;
		this.priceHour = priceHour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAverageProduction() {
		return averageProduction;
	}

	public void setAverageProduction(Double averageProduction) {
		this.averageProduction = averageProduction;
	}

	public Double getPriceHour() {
		return priceHour;
	}

	public void setPriceHour(Double priceHour) {
		this.priceHour = priceHour;
	}

}
