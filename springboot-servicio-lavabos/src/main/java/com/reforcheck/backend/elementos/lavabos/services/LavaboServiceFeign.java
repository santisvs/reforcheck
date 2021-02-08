package com.reforcheck.backend.elementos.lavabos.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo.Lavabo;
import com.reforcheck.backend.elementos.lavabos.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.lavabos.repositories.LavaboRepository;

@Service("serviceFeign")
public class LavaboServiceFeign implements LavaboService {

	@Autowired
	private LavaboRepository lavaboRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Lavabo> findAll() {
		ArrayList<Lavabo> lavabosList = (ArrayList<Lavabo>) lavaboRepository.findAll();

		for (Lavabo lavabo : lavabosList) {
			lavabo.setFabricantes(clienteFabricanteFeign.listarByReferencia(lavabo.getReferenciasFabricantes()));
		}

		return lavabosList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lavabo> findAllByIdElem(List<String> idElems) {
		List<Lavabo> result = new ArrayList<Lavabo>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lavabo> findAllByIdEstancia(String idEstancia) {
		List<Lavabo> lavabos = lavaboRepository.findByIdEstancia(idEstancia);
		for (Lavabo lavabo : lavabos) {
			lavabo.setFabricantes(clienteFabricanteFeign.listarByReferencia(lavabo.getReferenciasFabricantes()));
		}
		return lavabos;
	}

	@Override
	@Transactional(readOnly = true)
	public Lavabo findById(Long id) {
		Lavabo lavabo = lavaboRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		lavabo.setFabricantes(clienteFabricanteFeign.listarByReferencia(lavabo.getReferenciasFabricantes()));
		return lavabo;
	}

	@Override
	@Transactional(readOnly = true)
	public Lavabo findByIdElem(String idElem) {
		Lavabo lavabo = lavaboRepository.findByIdElem(idElem);
		lavabo.setFabricantes(clienteFabricanteFeign.listarByReferencia(lavabo.getReferenciasFabricantes()));
		return lavabo;
	}

	@Override
	@Transactional
	public List<Lavabo> saveAll(List<Lavabo> lavabos) {
		List<Lavabo> resultado = new ArrayList<Lavabo>();
		for (Lavabo lavabo : lavabos) {
			if (null == lavaboRepository.findByIdElem(lavabo.getIdElem())) {
				clienteFabricanteFeign.crear(lavabo.getFabricantes());
				resultado.add(lavaboRepository.save(lavabo));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Lavabo update(Lavabo lavabo, Long id) {
		Lavabo lavaboRepo = lavaboRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		lavaboRepo.setLavabo(lavabo);
		return lavaboRepository.save(lavaboRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		lavaboRepository.deleteById(id);
	}

}
