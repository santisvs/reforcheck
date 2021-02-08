package com.reforcheck.backend.fabricantes.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

public interface FabricanteService {

	public List<Fabricante> findAll();
	
	public List<Fabricante> findAllByReferencia(List<String> referencias);

	public Fabricante findById(Long id);
	
	public Fabricante findByReferencia(String referencia);
	
	public List<Fabricante> saveAll(List<Fabricante> fabricantes);

	public Fabricante update(Fabricante fabricante, Long id);

	public void delete(Long id);
	
	public void deleteByReferencia(String referencia);
}
