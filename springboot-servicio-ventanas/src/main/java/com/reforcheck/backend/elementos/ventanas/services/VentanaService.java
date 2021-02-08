package com.reforcheck.backend.elementos.ventanas.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.ventana.Ventana;

public interface VentanaService {

public List<Ventana> findAll();
	
	public List<Ventana> findAllByIdElem(List<String> idElem);
	
	public List<Ventana> findAllByIdEstancia(String idEstancia);

	public Ventana findById(Long id);
	
	public Ventana findByIdElem(String idElem);

	public List<Ventana> saveAll(List<Ventana> ventanas);

	public Ventana update(Ventana ventana, Long id);

	public void delete(Long id);
}
