package com.reforcheck.backend.elementos.radiadores.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.radiador.Radiador;
import com.reforcheck.backend.elementos.radiadores.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.radiadores.repositories.RadiadorRepository;

@Service("serviceFeign")
public class RadiadorServiceFeign implements RadiadorService {

	@Autowired
	private RadiadorRepository radiadorRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Radiador> findAll() {
		ArrayList<Radiador> radiadoresList = (ArrayList<Radiador>) radiadorRepository.findAll();

		for (Radiador radiador : radiadoresList) {
			radiador.setFabricantes(clienteFabricanteFeign.listarByReferencia(radiador.getReferenciasFabricantes()));
		}

		return radiadoresList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Radiador> findAllByIdElem(List<String> idElems) {
		List<Radiador> result = new ArrayList<Radiador>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Radiador> findAllByIdEstancia(String idEstancia) {
		List<Radiador> radiadores = radiadorRepository.findByIdEstancia(idEstancia);
		for (Radiador radiador : radiadores) {
			radiador.setFabricantes(clienteFabricanteFeign.listarByReferencia(radiador.getReferenciasFabricantes()));
		}
		return radiadores;
	}

	@Override
	@Transactional(readOnly = true)
	public Radiador findById(Long id) {
		Radiador radiador = radiadorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		radiador.setFabricantes(clienteFabricanteFeign.listarByReferencia(radiador.getReferenciasFabricantes()));
		return radiador;
	}

	@Override
	@Transactional(readOnly = true)
	public Radiador findByIdElem(String idElem) {
		Radiador radiador = radiadorRepository.findByIdElem(idElem);
		radiador.setFabricantes(clienteFabricanteFeign.listarByReferencia(radiador.getReferenciasFabricantes()));
		return radiador;
	}

	@Override
	@Transactional
	public List<Radiador> saveAll(List<Radiador> radiadores) {
		List<Radiador> resultado = new ArrayList<Radiador>();
		for (Radiador radiador : radiadores) {
			if (null == radiadorRepository.findByIdElem(radiador.getIdElem())) {
				clienteFabricanteFeign.crear(radiador.getFabricantes());
				resultado.add(radiadorRepository.save(radiador));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Radiador update(Radiador radiador, Long id) {
		Radiador radiadorRepo = radiadorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		radiadorRepo.setRadiador(radiador);
		return radiadorRepository.save(radiadorRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		radiadorRepository.deleteById(id);
	}

}
