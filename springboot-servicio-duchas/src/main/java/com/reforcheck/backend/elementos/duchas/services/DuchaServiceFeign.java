package com.reforcheck.backend.elementos.duchas.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.ducha.Ducha;
import com.reforcheck.backend.elementos.duchas.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.duchas.repositories.DuchaRepository;

@Service("serviceFeign")
public class DuchaServiceFeign implements DuchaService {

	@Autowired
	private DuchaRepository duchaRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Ducha> findAll() {
		ArrayList<Ducha> duchasList = (ArrayList<Ducha>) duchaRepository.findAll();

		for (Ducha ducha : duchasList) {
			ducha.setFabricantes(clienteFabricanteFeign.listarByReferencia(ducha.getReferenciasFabricantes()));
		}

		return duchasList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ducha> findAllByIdElem(List<String> idElems) {
		List<Ducha> result = new ArrayList<Ducha>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ducha> findAllByIdEstancia(String idEstancia) {
		List<Ducha> duchas = duchaRepository.findByIdEstancia(idEstancia);
		for (Ducha ducha : duchas) {
			ducha.setFabricantes(clienteFabricanteFeign.listarByReferencia(ducha.getReferenciasFabricantes()));
		}
		return duchas;
	}

	@Override
	@Transactional(readOnly = true)
	public Ducha findById(Long id) {
		Ducha ducha = duchaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		ducha.setFabricantes(clienteFabricanteFeign.listarByReferencia(ducha.getReferenciasFabricantes()));
		return ducha;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Ducha findByIdElem(String idElem) {
		Ducha ducha = duchaRepository.findByIdElem(idElem);
		ducha.setFabricantes(clienteFabricanteFeign.listarByReferencia(ducha.getReferenciasFabricantes()));
		return ducha;
	}

	@Override
	@Transactional
	public List<Ducha> saveAll(List<Ducha> duchas) {
		List<Ducha> resultado = new ArrayList<Ducha>();
		for (Ducha ducha : duchas) {
			if(null == duchaRepository.findByIdElem(ducha.getIdElem())) {
				clienteFabricanteFeign.crear(ducha.getFabricantes());
				resultado.add(duchaRepository.save(ducha));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Ducha update(Ducha ducha, Long id) {
		Ducha duchaRepo = duchaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		duchaRepo.setDucha(ducha);
		return duchaRepository.save(duchaRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		duchaRepository.deleteById(id);
	}

}
