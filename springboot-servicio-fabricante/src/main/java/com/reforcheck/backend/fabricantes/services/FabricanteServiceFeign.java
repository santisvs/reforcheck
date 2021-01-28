package com.reforcheck.backend.fabricantes.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;
import com.reforcheck.backend.fabricantes.repositories.FabricanteRepository;

@Service("serviceFeign")
public class FabricanteServiceFeign implements FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Fabricante> findAll() {
		return (List<Fabricante>) fabricanteRepository.findAll();
	}

	@Override
	public List<Fabricante> findAllByReferencia(List<String> referencias) {
		List<Fabricante> result = new ArrayList<Fabricante>();
		for (String referencia : referencias) {
			result.add(fabricanteRepository.findByReferencia(referencia));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Fabricante findById(Long id) {
		return fabricanteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	@Transactional(readOnly = true)
	public Fabricante findByReferencia(String referencia) {
		return fabricanteRepository.findByReferencia(referencia);
	}

	@Override
	@Transactional
	public List<Fabricante> saveAll(List<Fabricante> fabricantes) {
		List<Fabricante> resultado = new ArrayList<Fabricante>();
		for (Fabricante fabricante : fabricantes) {
			if (null == fabricanteRepository.findByReferencia(fabricante.getReferencia())) {
				resultado.add(fabricanteRepository.save(fabricante));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Fabricante update(Fabricante fabricante, Long id) {
		Fabricante fabricanteRepo = fabricanteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		fabricanteRepo.setFabricante(fabricante);
		return fabricanteRepository.save(fabricanteRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		fabricanteRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteByReferencia(String referencia) {
		fabricanteRepository.deleteByReferencia(referencia);
	}

}
