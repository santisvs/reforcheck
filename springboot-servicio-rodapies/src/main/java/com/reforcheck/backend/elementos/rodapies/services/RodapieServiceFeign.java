package com.reforcheck.backend.elementos.rodapies.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.rodapie.Rodapie;
import com.reforcheck.backend.elementos.rodapies.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.rodapies.repositories.RodapieRepository;

@Service("serviceFeign")
public class RodapieServiceFeign implements RodapieService {

	@Autowired
	private RodapieRepository rodapieRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Rodapie> findAll() {
		ArrayList<Rodapie> rodapieesList = (ArrayList<Rodapie>) rodapieRepository.findAll();

		for (Rodapie rodapie : rodapieesList) {
			rodapie
					.setFabricantes(clienteFabricanteFeign.listarByReferencia(rodapie.getReferenciasFabricantes()));
		}

		return rodapieesList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rodapie> findAllByIdElem(List<String> idElems) {
		List<Rodapie> result = new ArrayList<Rodapie>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Rodapie findByIdEstancia(String idEstancia) {
		Rodapie rodapie = rodapieRepository.findByIdEstancia(idEstancia);
		rodapie.setFabricantes(clienteFabricanteFeign.listarByReferencia(rodapie.getReferenciasFabricantes()));

		return rodapie;
	}

	@Override
	@Transactional(readOnly = true)
	public Rodapie findById(Long id) {
		Rodapie rodapie = rodapieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		rodapie.setFabricantes(clienteFabricanteFeign.listarByReferencia(rodapie.getReferenciasFabricantes()));
		return rodapie;
	}

	@Override
	@Transactional(readOnly = true)
	public Rodapie findByIdElem(String idElem) {
		Rodapie rodapie = rodapieRepository.findByIdElem(idElem);
		rodapie.setFabricantes(clienteFabricanteFeign.listarByReferencia(rodapie.getReferenciasFabricantes()));
		return rodapie;
	}

	@Override
	@Transactional
	public Rodapie save(Rodapie rodapie) {
		Rodapie resultado = null;
		if (null == rodapieRepository.findByIdElem(rodapie.getIdElem())) {
			clienteFabricanteFeign.crear(rodapie.getFabricantes());
			resultado = rodapieRepository.save(rodapie);
		}
		return resultado;
	}

	@Override
	@Transactional
	public Rodapie update(Rodapie rodapie, Long id) {
		Rodapie rodapieRepo = rodapieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		rodapieRepo.setRodapie(rodapie);
		return rodapieRepository.save(rodapieRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		rodapieRepository.deleteById(id);
	}

}
