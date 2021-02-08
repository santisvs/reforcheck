package com.reforcheck.backend.elementos.mobiliarioobras.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.mobiliarioobra.MobiliarioObra;

public interface MobiliarioObraService {
	
	public List<MobiliarioObra> findAll();
	
	public List<MobiliarioObra> findAllByIdElem(List<String> idElem);
	
	public MobiliarioObra findByIdEstancia(String idEstancia);

	public MobiliarioObra findById(Long id);
	
	public MobiliarioObra findByIdElem(String idElem);

	public MobiliarioObra save(MobiliarioObra mobiliarioObra);

	public MobiliarioObra update(MobiliarioObra mobiliarioObra, Long id);

	public void delete(Long id);

}
