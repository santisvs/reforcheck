package com.reforcheck.backend.commons.entities.mysql.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <b>Unit</b> <br>
 * Entidad de base de datos con lo siguientes atributos:
 * 
 * <ul>
 * <li>id: Identificador de la unidad en BBDD</li>
 * <li>name: Nombre de la unidad</li>
 * <li>description: Descripción de la unidad</li>
 * <li>priceHour: Valor que indica el precio/hora de la unidad</li>
 * <li>expectedHour: Valor que indica la horas estimadas en ejecutar la
 * unidad</li>
 * <li>performance: Valor que indica el rendimiento esperado en la unidad</li>
 * <li>measurementUnits: Unidad de medida de la unidad</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
@Entity
@Table(name = "units")
public class Unit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@Column(name = "price_hour")
	private Double priceHour;
	@Column(name = "expected_hour")
	private Double expectedHour;
	private Double performance;
	@Column(name = "measurement_unit")
	private UnidadMedida measurementUnit;
	
	public void setUnit(Unit unit) {
		setName(unit.getName());
		setDescription(unit.getDescription());
		setPriceHour(unit.getPriceHour());
		setExpectedHour(unit.getExpectedHour());
		setPerformance(unit.getPerformance());
		setMeasurementUnit(unit.getMeasurementUnit());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPriceHour() {
		return priceHour;
	}

	public void setPriceHour(Double priceHour) {
		this.priceHour = priceHour;
	}

	public Double getExpectedHour() {
		return expectedHour;
	}

	public void setExpectedHour(Double expectedHour) {
		this.expectedHour = expectedHour;
	}

	public Double getPerformance() {
		return performance;
	}

	public void setPerformance(Double performance) {
		this.performance = performance;
	}

	public UnidadMedida getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(UnidadMedida measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

}
