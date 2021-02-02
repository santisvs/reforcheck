package com.reforcheck.backend.propiedades.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.propiedad.Propiedad;

public interface PropiedadService {
	
	public List<Propiedad> findAll();
	
	public List<Propiedad> findAllByIdPropiedad(List<String> referencias);
	
	public List<Propiedad> findAllByIdPropietario(String idPropietario);

	public Propiedad findById(Long id);
	
	public Propiedad findByIdPropiedad(String referencia);

	public List<Propiedad> saveAll(List<Propiedad> propiedads);

	public Propiedad update(Propiedad propiedad, Long id);

	public void delete(Long id);

}
