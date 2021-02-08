package com.reforcheck.backend.elementos.bidets.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.bidet.Bidet;
import com.reforcheck.backend.elementos.bidets.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.bidets.repositories.BidetRepository;

@Service("serviceFeign")
public class BidetServiceFeign implements BidetService {
	
	@Autowired
	private BidetRepository bidetRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Bidet> findAll() {
		ArrayList<Bidet> bidetsList = (ArrayList<Bidet>) bidetRepository.findAll();

		for (Bidet bidet : bidetsList) {
			bidet.setFabricantes(clienteFabricanteFeign.listarByReferencia(bidet.getReferenciasFabricantes()));
		}

		return bidetsList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Bidet> findAllByIdElem(List<String> idElems) {
		List<Bidet> result = new ArrayList<Bidet>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Bidet> findAllByIdEstancia(String idEstancia) {
		List<Bidet> bidets = bidetRepository.findByIdEstancia(idEstancia);
		for (Bidet bidet : bidets) {
			bidet.setFabricantes(clienteFabricanteFeign.listarByReferencia(bidet.getReferenciasFabricantes()));
		}
		return bidets;
	}

	@Override
	@Transactional(readOnly = true)
	public Bidet findById(Long id) {
		Bidet bidet = bidetRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		bidet.setFabricantes(clienteFabricanteFeign.listarByReferencia(bidet.getReferenciasFabricantes()));
		return bidet;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Bidet findByIdElem(String idElem) {
		Bidet bidet = bidetRepository.findByIdElem(idElem);
		bidet.setFabricantes(clienteFabricanteFeign.listarByReferencia(bidet.getReferenciasFabricantes()));
		return bidet;
	}

	@Override
	@Transactional
	public List<Bidet> saveAll(List<Bidet> bidets) {
		List<Bidet> resultado = new ArrayList<Bidet>();
		for (Bidet bidet : bidets) {
			if(null == bidetRepository.findByIdElem(bidet.getIdElem())) {
				clienteFabricanteFeign.crear(bidet.getFabricantes());
				resultado.add(bidetRepository.save(bidet));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Bidet update(Bidet bidet, Long id) {
		Bidet bidetRepo = bidetRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		bidetRepo.setBidet(bidet);
		return bidetRepository.save(bidetRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		bidetRepository.deleteById(id);
	}

}
