package com.reforcheck.backend.elementos.techos.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.techo.Techo;

public interface TechoService {
	
	public List<Techo> findAll();
	
	public List<Techo> findAllByIdElem(List<String> idElem);
	
	public Techo findByIdEstancia(String idEstancia);

	public Techo findById(Long id);
	
	public Techo findByIdElem(String idElem);

	public Techo save(Techo techo);

	public Techo update(Techo techo, Long id);

	public void delete(Long id);

}
