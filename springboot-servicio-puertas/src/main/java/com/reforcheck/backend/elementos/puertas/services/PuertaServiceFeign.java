package com.reforcheck.backend.elementos.puertas.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.puerta.Puerta;
import com.reforcheck.backend.elementos.puertas.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.puertas.repositories.PuertaRepository;

@Service("serviceFeign")
public class PuertaServiceFeign implements PuertaService {

	@Autowired
	private PuertaRepository puertaRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Puerta> findAll() {
		ArrayList<Puerta> puertasList = (ArrayList<Puerta>) puertaRepository.findAll();

		for (Puerta puerta : puertasList) {
			puerta.setFabricantes(clienteFabricanteFeign.listarByReferencia(puerta.getReferenciasFabricantes()));
		}

		return puertasList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Puerta> findAllByIdElem(List<String> idElems) {
		List<Puerta> result = new ArrayList<Puerta>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Puerta> findAllByIdEstancia(String idEstancia) {
		List<Puerta> puertas = puertaRepository.findByIdEstancia(idEstancia);
		for (Puerta puerta : puertas) {
			puerta.setFabricantes(clienteFabricanteFeign.listarByReferencia(puerta.getReferenciasFabricantes()));
		}
		return puertas;
	}

	@Override
	@Transactional(readOnly = true)
	public Puerta findById(Long id) {
		Puerta puerta = puertaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		puerta.setFabricantes(clienteFabricanteFeign.listarByReferencia(puerta.getReferenciasFabricantes()));
		return puerta;
	}

	@Override
	@Transactional(readOnly = true)
	public Puerta findByIdElem(String idElem) {
		Puerta puerta = puertaRepository.findByIdElem(idElem);
		puerta.setFabricantes(clienteFabricanteFeign.listarByReferencia(puerta.getReferenciasFabricantes()));
		return puerta;
	}

	@Override
	@Transactional
	public List<Puerta> saveAll(List<Puerta> puertas) {
		List<Puerta> resultado = new ArrayList<Puerta>();
		for (Puerta puerta : puertas) {
			if (null == puertaRepository.findByIdElem(puerta.getIdElem())) {
				clienteFabricanteFeign.crear(puerta.getFabricantes());
				resultado.add(puertaRepository.save(puerta));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Puerta update(Puerta puerta, Long id) {
		Puerta puertaRepo = puertaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		puertaRepo.setPuerta(puerta);
		return puertaRepository.save(puertaRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		puertaRepository.deleteById(id);
	}

}
