package com.reforcheck.backend.elementos.duchas.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.ducha.Ducha;

public interface DuchaService {

public List<Ducha> findAll();
	
	public List<Ducha> findAllByIdElem(List<String> idElem);
	
	public List<Ducha> findAllByIdEstancia(String idEstancia);

	public Ducha findById(Long id);
	
	public Ducha findByIdElem(String idElem);

	public List<Ducha> saveAll(List<Ducha> duchas);

	public Ducha update(Ducha ducha, Long id);

	public void delete(Long id);

}

