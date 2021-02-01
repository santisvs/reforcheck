package com.reforcheck.backend.elementos.revestimientos.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.revestimiento.Revestimiento;

public interface RevestimientoService {
	
	public List<Revestimiento> findAll();
	
	public List<Revestimiento> findAllByIdElem(List<String> idElem);
	
	public Revestimiento findByIdEstancia(String idEstancia);

	public Revestimiento findById(Long id);
	
	public Revestimiento findByIdElem(String idElem);

	public Revestimiento save(Revestimiento revestimiento);

	public Revestimiento update(Revestimiento revestimiento, Long id);

	public void delete(Long id);

}
