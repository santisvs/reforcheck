package com.reforcheck.backend.elementos.solados.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.solado.Solado;

public interface SoladoService {
	
	public List<Solado> findAll();
	
	public List<Solado> findAllByIdElem(List<String> idElem);
	
	public Solado findByIdEstancia(String idEstancia);

	public Solado findById(Long id);
	
	public Solado findByIdElem(String idElem);

	public Solado save(Solado solado);

	public Solado update(Solado solado, Long id);

	public void delete(Long id);

}
