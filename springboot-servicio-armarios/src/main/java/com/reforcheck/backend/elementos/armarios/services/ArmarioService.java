package com.reforcheck.backend.elementos.armarios.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.armario.Armario;

public interface ArmarioService {
	
	public List<Armario> findAll();
	
	public List<Armario> findAllByIdElem(List<String> idElem);
	
	public List<Armario> findAllByIdEstancia(String idEstancia);

	public Armario findById(Long id);
	
	public Armario findByIdElem(String idElem);

	public List<Armario> saveAll(List<Armario> armarios);

	public Armario update(Armario armario, Long id);

	public void delete(Long id);

}
