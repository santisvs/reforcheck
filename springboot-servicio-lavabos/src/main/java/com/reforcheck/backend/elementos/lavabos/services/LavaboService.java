package com.reforcheck.backend.elementos.lavabos.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo.Lavabo;

public interface LavaboService {

public List<Lavabo> findAll();
	
	public List<Lavabo> findAllByIdElem(List<String> idElem);
	
	public List<Lavabo> findAllByIdEstancia(String idEstancia);

	public Lavabo findById(Long id);
	
	public Lavabo findByIdElem(String idElem);

	public List<Lavabo> saveAll(List<Lavabo> lavabos);

	public Lavabo update(Lavabo lavabo, Long id);

	public void delete(Long id);
}
