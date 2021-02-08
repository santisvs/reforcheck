package com.reforcheck.backend.elementos.inodoros.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.inodoro.Inodoro;
import com.reforcheck.backend.elementos.inodoros.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.inodoros.repositories.InodoroRepository;

@Service("serviceFeign")
public class InodoroServiceFeign implements InodoroService {

	@Autowired
	private InodoroRepository inodoroRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Inodoro> findAll() {
		ArrayList<Inodoro> inodorosList = (ArrayList<Inodoro>) inodoroRepository.findAll();

		for (Inodoro inodoro : inodorosList) {
			inodoro.setFabricantes(clienteFabricanteFeign.listarByReferencia(inodoro.getReferenciasFabricantes()));
		}

		return inodorosList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Inodoro> findAllByIdElem(List<String> idElems) {
		List<Inodoro> result = new ArrayList<Inodoro>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Inodoro> findAllByIdEstancia(String idEstancia) {
		List<Inodoro> inodoros = inodoroRepository.findByIdEstancia(idEstancia);
		for (Inodoro inodoro : inodoros) {
			inodoro.setFabricantes(clienteFabricanteFeign.listarByReferencia(inodoro.getReferenciasFabricantes()));
		}
		return inodoros;
	}

	@Override
	@Transactional(readOnly = true)
	public Inodoro findById(Long id) {
		Inodoro inodoro = inodoroRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		inodoro.setFabricantes(clienteFabricanteFeign.listarByReferencia(inodoro.getReferenciasFabricantes()));
		return inodoro;
	}

	@Override
	@Transactional(readOnly = true)
	public Inodoro findByIdElem(String idElem) {
		Inodoro inodoro = inodoroRepository.findByIdElem(idElem);
		inodoro.setFabricantes(clienteFabricanteFeign.listarByReferencia(inodoro.getReferenciasFabricantes()));
		return inodoro;
	}

	@Override
	@Transactional
	public List<Inodoro> saveAll(List<Inodoro> inodoros) {
		List<Inodoro> resultado = new ArrayList<Inodoro>();
		for (Inodoro inodoro : inodoros) {
			if (null == inodoroRepository.findByIdElem(inodoro.getIdElem())) {
				clienteFabricanteFeign.crear(inodoro.getFabricantes());
				resultado.add(inodoroRepository.save(inodoro));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Inodoro update(Inodoro inodoro, Long id) {
		Inodoro inodoroRepo = inodoroRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		inodoroRepo.setInodoro(inodoro);
		return inodoroRepository.save(inodoroRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		inodoroRepository.deleteById(id);
	}

}
