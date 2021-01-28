package com.reforcheck.backend.elementos.iluminaciones.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.iluminacion.Iluminacion;

public interface IluminacionService {
	
	public List<Iluminacion> findAll();
	
	public List<Iluminacion> findAllByIdElem(List<String> idElem);
	
	public Iluminacion findByIdEstancia(String idEstancia);

	public Iluminacion findById(Long id);
	
	public Iluminacion findByIdElem(String idElem);

	public Iluminacion save(Iluminacion iluminacion);

	public Iluminacion update(Iluminacion iluminacion, Long id);

	public void delete(Long id);

}
