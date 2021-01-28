package com.reforcheck.backend.elementos.iluminaciones.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.iluminacion.Iluminacion;
import com.reforcheck.backend.elementos.iluminaciones.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.iluminaciones.repositories.IluminacionRepository;

@Service("serviceFeign")
public class IluminacionServiceFeign implements IluminacionService {

	@Autowired
	private IluminacionRepository iluminacionRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Iluminacion> findAll() {
		ArrayList<Iluminacion> iluminacionesList = (ArrayList<Iluminacion>) iluminacionRepository.findAll();

		for (Iluminacion iluminacion : iluminacionesList) {
			iluminacion
					.setFabricantes(clienteFabricanteFeign.listarByReferencia(iluminacion.getReferenciasFabricantes()));
		}

		return iluminacionesList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Iluminacion> findAllByIdElem(List<String> idElems) {
		List<Iluminacion> result = new ArrayList<Iluminacion>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Iluminacion findByIdEstancia(String idEstancia) {
		Iluminacion iluminacion = iluminacionRepository.findByIdEstancia(idEstancia);
		iluminacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(iluminacion.getReferenciasFabricantes()));
		
		return iluminacion;
	}

	@Override
	@Transactional(readOnly = true)
	public Iluminacion findById(Long id) {
		Iluminacion iluminacion = iluminacionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		iluminacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(iluminacion.getReferenciasFabricantes()));
		return iluminacion;
	}

	@Override
	@Transactional(readOnly = true)
	public Iluminacion findByIdElem(String idElem) {
		Iluminacion iluminacion = iluminacionRepository.findByIdElem(idElem);
		iluminacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(iluminacion.getReferenciasFabricantes()));
		return iluminacion;
	}

	@Override
	@Transactional
	public Iluminacion save(Iluminacion iluminacion) {
		Iluminacion resultado = null;
		if (null == iluminacionRepository.findByIdElem(iluminacion.getIdElem())) {
			clienteFabricanteFeign.crear(iluminacion.getFabricantes());
			resultado = iluminacionRepository.save(iluminacion);
		}
		return resultado;
	}

	@Override
	@Transactional
	public Iluminacion update(Iluminacion iluminacion, Long id) {
		Iluminacion iluminacionRepo = iluminacionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		iluminacionRepo.setIluminacion(iluminacion);
		return iluminacionRepository.save(iluminacionRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		iluminacionRepository.deleteById(id);
	}

}
