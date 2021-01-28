package com.reforcheck.backend.elementos.instalaciones.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.instalacion.Instalacion;

public interface InstalacionService {
	
	public List<Instalacion> findAll();
	
	public List<Instalacion> findAllByIdElem(List<String> idElem);
	
	public Instalacion findByIdEstancia(String idEstancia);

	public Instalacion findById(Long id);
	
	public Instalacion findByIdElem(String idElem);

	public Instalacion save(Instalacion instalacion);

	public Instalacion update(Instalacion instalacion, Long id);

	public void delete(Long id);

}
