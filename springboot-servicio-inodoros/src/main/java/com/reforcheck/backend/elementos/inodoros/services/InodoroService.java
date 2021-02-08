package com.reforcheck.backend.elementos.inodoros.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.inodoro.Inodoro;

public interface InodoroService {

public List<Inodoro> findAll();
	
	public List<Inodoro> findAllByIdElem(List<String> idElem);
	
	public List<Inodoro> findAllByIdEstancia(String idEstancia);

	public Inodoro findById(Long id);
	
	public Inodoro findByIdElem(String idElem);

	public List<Inodoro> saveAll(List<Inodoro> inodoros);

	public Inodoro update(Inodoro inodoro, Long id);

	public void delete(Long id);
}
