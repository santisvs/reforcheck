package com.reforcheck.backend.elementos.techos.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.techo.Techo;
import com.reforcheck.backend.elementos.techos.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.techos.repositories.TechoRepository;

@Service("serviceFeign")
public class TechoServiceFeign implements TechoService {

	@Autowired
	private TechoRepository techoRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Techo> findAll() {
		ArrayList<Techo> techoesList = (ArrayList<Techo>) techoRepository.findAll();

		for (Techo techo : techoesList) {
			techo
					.setFabricantes(clienteFabricanteFeign.listarByReferencia(techo.getReferenciasFabricantes()));
		}

		return techoesList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Techo> findAllByIdElem(List<String> idElems) {
		List<Techo> result = new ArrayList<Techo>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Techo findByIdEstancia(String idEstancia) {
		Techo techo = techoRepository.findByIdEstancia(idEstancia);
		techo.setFabricantes(clienteFabricanteFeign.listarByReferencia(techo.getReferenciasFabricantes()));

		return techo;
	}

	@Override
	@Transactional(readOnly = true)
	public Techo findById(Long id) {
		Techo techo = techoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		techo.setFabricantes(clienteFabricanteFeign.listarByReferencia(techo.getReferenciasFabricantes()));
		return techo;
	}

	@Override
	@Transactional(readOnly = true)
	public Techo findByIdElem(String idElem) {
		Techo techo = techoRepository.findByIdElem(idElem);
		techo.setFabricantes(clienteFabricanteFeign.listarByReferencia(techo.getReferenciasFabricantes()));
		return techo;
	}

	@Override
	@Transactional
	public Techo save(Techo techo) {
		Techo resultado = null;
		if (null == techoRepository.findByIdElem(techo.getIdElem())) {
			clienteFabricanteFeign.crear(techo.getFabricantes());
			resultado = techoRepository.save(techo);
		}
		return resultado;
	}

	@Override
	@Transactional
	public Techo update(Techo techo, Long id) {
		Techo techoRepo = techoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		techoRepo.setTecho(techo);
		return techoRepository.save(techoRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		techoRepository.deleteById(id);
	}

}
