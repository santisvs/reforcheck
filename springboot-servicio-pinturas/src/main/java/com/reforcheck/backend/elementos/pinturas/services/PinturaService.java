package com.reforcheck.backend.elementos.pinturas.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.pintura.Pintura;

public interface PinturaService {
	
	public List<Pintura> findAll();
	
	public List<Pintura> findAllByIdElem(List<String> idElem);
	
	public Pintura findByIdEstancia(String idEstancia);

	public Pintura findById(Long id);
	
	public Pintura findByIdElem(String idElem);

	public Pintura save(Pintura pintura);

	public Pintura update(Pintura pintura, Long id);

	public void delete(Long id);

}
