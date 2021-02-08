package com.reforcheck.backend.elementos.baneras.services;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.banera.Banera;

public interface BaneraService {

	public List<Banera> findAll();

	public List<Banera> findAllByIdElem(List<String> idElem);

	public List<Banera> findAllByIdEstancia(String idEstancia);

	public Banera findById(Long id);

	public Banera findByIdElem(String idElem);

	public List<Banera> saveAll(List<Banera> baneras);

	public Banera update(Banera banera, Long id);

	public void delete(Long id);

}
