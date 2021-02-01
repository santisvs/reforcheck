package com.reforcheck.backend.elementos.puertas.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.puerta.Puerta;

public interface PuertaService {

public List<Puerta> findAll();
	
	public List<Puerta> findAllByIdElem(List<String> idElem);
	
	public List<Puerta> findAllByIdEstancia(String idEstancia);

	public Puerta findById(Long id);
	
	public Puerta findByIdElem(String idElem);

	public List<Puerta> saveAll(List<Puerta> puertas);

	public Puerta update(Puerta puerta, Long id);

	public void delete(Long id);
}
