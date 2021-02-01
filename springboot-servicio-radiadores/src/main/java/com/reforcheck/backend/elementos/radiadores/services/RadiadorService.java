package com.reforcheck.backend.elementos.radiadores.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.radiador.Radiador;

public interface RadiadorService {

public List<Radiador> findAll();
	
	public List<Radiador> findAllByIdElem(List<String> idElem);
	
	public List<Radiador> findAllByIdEstancia(String idEstancia);

	public Radiador findById(Long id);
	
	public Radiador findByIdElem(String idElem);

	public List<Radiador> saveAll(List<Radiador> radiadores);

	public Radiador update(Radiador radiador, Long id);

	public void delete(Long id);
}
