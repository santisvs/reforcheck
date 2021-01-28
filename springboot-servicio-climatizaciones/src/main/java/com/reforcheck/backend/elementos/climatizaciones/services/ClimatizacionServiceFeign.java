package com.reforcheck.backend.elementos.climatizaciones.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.climatizacion.Climatizacion;
import com.reforcheck.backend.elementos.climatizaciones.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.climatizaciones.repositories.ClimatizacionRepository;

@Service("serviceFeign")
public class ClimatizacionServiceFeign implements ClimatizacionService {

	@Autowired
	private ClimatizacionRepository climatizacionRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Climatizacion> findAll() {
		ArrayList<Climatizacion> climatizacionesList = (ArrayList<Climatizacion>) climatizacionRepository.findAll();

		for (Climatizacion climatizacion : climatizacionesList) {
			climatizacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(climatizacion.getReferenciasFabricantes()));
		}

		return climatizacionesList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Climatizacion> findAllByIdElem(List<String> idElems) {
		List<Climatizacion> result = new ArrayList<Climatizacion>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Climatizacion> findAllByIdEstancia(String idEstancia) {
		List<Climatizacion> climatizaciones = climatizacionRepository.findByIdEstancia(idEstancia);
		for (Climatizacion climatizacion : climatizaciones) {
			climatizacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(climatizacion.getReferenciasFabricantes()));
		}
		return climatizaciones;
	}

	@Override
	@Transactional(readOnly = true)
	public Climatizacion findById(Long id) {
		Climatizacion climatizacion = climatizacionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		climatizacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(climatizacion.getReferenciasFabricantes()));
		return climatizacion;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Climatizacion findByIdElem(String idElem) {
		Climatizacion climatizacion = climatizacionRepository.findByIdElem(idElem);
		climatizacion.setFabricantes(clienteFabricanteFeign.listarByReferencia(climatizacion.getReferenciasFabricantes()));
		return climatizacion;
	}

	@Override
	@Transactional
	public List<Climatizacion> saveAll(List<Climatizacion> climatizaciones) {
		List<Climatizacion> resultado = new ArrayList<Climatizacion>();
		for (Climatizacion climatizacion : climatizaciones) {
			if(null == climatizacionRepository.findByIdElem(climatizacion.getIdElem())) {
				clienteFabricanteFeign.crear(climatizacion.getFabricantes());
				resultado.add(climatizacionRepository.save(climatizacion));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Climatizacion update(Climatizacion climatizacion, Long id) {
		Climatizacion climatizacionRepo = climatizacionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		climatizacionRepo.setClimatizacion(climatizacion);
		return climatizacionRepository.save(climatizacionRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		climatizacionRepository.deleteById(id);
	}

}
