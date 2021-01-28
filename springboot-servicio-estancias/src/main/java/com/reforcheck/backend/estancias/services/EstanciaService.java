package com.reforcheck.backend.estancias.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.estancia.Estancia;

public interface EstanciaService {
	
	public List<Estancia> findAll();
	
	public List<Estancia> findAllByIdEstancia(List<String> referencias);
	
	public List<Estancia> findAllByIdPlanta(String idPlanta);

	public Estancia findById(Long id);
	
	public Estancia findByIdEstancia(String referencia);

	public List<Estancia> saveAll(List<Estancia> estancias);

	public Estancia update(Estancia estancia, Long id);

	public void delete(Long id);

}
