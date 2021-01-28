package com.reforcheck.backend.elementos.climatizaciones.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.climatizacion.Climatizacion;

public interface ClimatizacionService {

	public List<Climatizacion> findAll();
	
	public List<Climatizacion> findAllByIdElem(List<String> idElem);
	
	public List<Climatizacion> findAllByIdEstancia(String idEstancia);

	public Climatizacion findById(Long id);
	
	public Climatizacion findByIdElem(String idElem);

	public List<Climatizacion> saveAll(List<Climatizacion> climatizaciones);

	public Climatizacion update(Climatizacion climatizacion, Long id);

	public void delete(Long id);

}
