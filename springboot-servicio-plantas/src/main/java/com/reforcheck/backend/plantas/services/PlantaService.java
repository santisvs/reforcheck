package com.reforcheck.backend.plantas.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.planta.Planta;

public interface PlantaService {
	
	public List<Planta> findAll();
	
	public List<Planta> findAllByIdPlanta(List<String> referencias);
	
	public List<Planta> findAllByIdPropiedad(String idPropiedad);

	public Planta findById(Long id);
	
	public Planta findByIdPlanta(String referencia);

	public List<Planta> saveAll(List<Planta> plantas);

	public Planta update(Planta planta, Long id);

	public void delete(Long id);

}
