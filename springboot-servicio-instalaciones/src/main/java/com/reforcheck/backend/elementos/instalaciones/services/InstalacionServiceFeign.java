package com.reforcheck.backend.elementos.instalaciones.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.instalacion.Instalacion;
import com.reforcheck.backend.elementos.instalaciones.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.instalaciones.repositories.InstalacionRepository;

@Service("serviceFeign")
public class InstalacionServiceFeign implements InstalacionService {

	@Autowired
	private InstalacionRepository instalacionRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Instalacion> findAll() {
		ArrayList<Instalacion> instalacionesList = (ArrayList<Instalacion>) instalacionRepository.findAll();

		for (Instalacion instalacion : instalacionesList) {
			instalacion
					.setFabricantes(clienteFabricanteFeign.listarByReferencia(instalacion.getReferenciasFabricantes()));
		}

		return instalacionesList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Instalacion> findAllByIdElem(List<String> idElems) {
		List<Instalacion> result = new ArrayList<Instalacion>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Instalacion findByIdEstancia(String idEstancia) {
		Instalacion instalacion = instalacionRepository.findByIdEstancia(idEstancia);
		instalacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(instalacion.getReferenciasFabricantes()));

		return instalacion;
	}

	@Override
	@Transactional(readOnly = true)
	public Instalacion findById(Long id) {
		Instalacion instalacion = instalacionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		instalacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(instalacion.getReferenciasFabricantes()));
		return instalacion;
	}

	@Override
	@Transactional(readOnly = true)
	public Instalacion findByIdElem(String idElem) {
		Instalacion instalacion = instalacionRepository.findByIdElem(idElem);
		instalacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(instalacion.getReferenciasFabricantes()));
		return instalacion;
	}

	@Override
	@Transactional
	public Instalacion save(Instalacion instalacion) {
		Instalacion resultado = null;
		if (null == instalacionRepository.findByIdElem(instalacion.getIdElem())) {
			clienteFabricanteFeign.crear(instalacion.getFabricantes());
			resultado = instalacionRepository.save(instalacion);
		}
		return resultado;
	}

	@Override
	@Transactional
	public Instalacion update(Instalacion instalacion, Long id) {
		Instalacion instalacionRepo = instalacionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		instalacionRepo.setInstalacion(instalacion);
		return instalacionRepository.save(instalacionRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		instalacionRepository.deleteById(id);
	}

}
