package com.reforcheck.backend.elementos.bidets.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.bidet.Bidet;

public interface BidetService {

public List<Bidet> findAll();
	
	public List<Bidet> findAllByIdElem(List<String> idElem);
	
	public List<Bidet> findAllByIdEstancia(String idEstancia);

	public Bidet findById(Long id);
	
	public Bidet findByIdElem(String idElem);

	public List<Bidet> saveAll(List<Bidet> bidets);

	public Bidet update(Bidet bidet, Long id);

	public void delete(Long id);
}
