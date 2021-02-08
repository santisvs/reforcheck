package com.reforcheck.backend.elementos.rodapies.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.rodapie.Rodapie;

public interface RodapieService {
	
	public List<Rodapie> findAll();
	
	public List<Rodapie> findAllByIdElem(List<String> idElem);
	
	public Rodapie findByIdEstancia(String idEstancia);

	public Rodapie findById(Long id);
	
	public Rodapie findByIdElem(String idElem);

	public Rodapie save(Rodapie rodapie);

	public Rodapie update(Rodapie rodapie, Long id);

	public void delete(Long id);

}
