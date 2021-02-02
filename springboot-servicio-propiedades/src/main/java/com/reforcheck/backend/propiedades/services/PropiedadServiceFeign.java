package com.reforcheck.backend.propiedades.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.planta.Planta;
import com.reforcheck.backend.commons.entities.mysql.models.propiedad.Propiedad;
import com.reforcheck.backend.propiedades.clients.PlantaClientRest;
import com.reforcheck.backend.propiedades.repositories.PropiedadRepository;

@Service("serviceFeign")
public class PropiedadServiceFeign implements PropiedadService {

	@Autowired
	private PropiedadRepository propiedadDao;
	@Autowired
	private PlantaClientRest clientePlantaFeign;
	
	@Override
	@Transactional(readOnly = true)
	public List<Propiedad> findAll() {

		ArrayList<Propiedad> propiedadesList = (ArrayList<Propiedad>) propiedadDao.findAll();

		for (Propiedad propiedad : propiedadesList) {
			propiedad.setPlantas(clientePlantaFeign.listarByIdPropiedad(propiedad.getIdPropiedad()).stream()
					.map(p -> new Planta(p)).collect(Collectors.toList()));
		}

		return propiedadesList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Propiedad> findAllByIdPropiedad(List<String> referencias) {
		ArrayList<Propiedad> propiedadesList = new ArrayList<Propiedad>();
		for (String referencia : referencias) {
			propiedadesList.add(findByIdPropiedad(referencia));
		}
		return propiedadesList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Propiedad> findAllByIdPropietario(String idPropietario){
		List<Propiedad> propiedadesList = propiedadDao.findByIdPropietario(idPropietario);
		for (Propiedad propiedad : propiedadesList) {
			propiedad.setPlantas(clientePlantaFeign.listarByIdPropiedad(propiedad.getIdPropiedad()).stream()
					.map(p -> new Planta(p)).collect(Collectors.toList()));
		}
		return propiedadesList;
	}


	@Override
	@Transactional(readOnly = true)
	public Propiedad findById(Long id) {
		Propiedad propiedad = propiedadDao.findById(id).orElseThrow(EntityNotFoundException::new);
		propiedad.setPlantas(clientePlantaFeign.listarByIdPropiedad(propiedad.getIdPropiedad()).stream()
				.map(p -> new Planta(p)).collect(Collectors.toList()));
		return propiedad;
	}

	@Override
	@Transactional(readOnly = true)
	public Propiedad findByIdPropiedad(String referencia) {
		Propiedad propiedad = propiedadDao.findByIdPropiedad(referencia);
		propiedad.setPlantas(clientePlantaFeign.listarByIdPropiedad(propiedad.getIdPropiedad()).stream()
				.map(p -> new Planta(p)).collect(Collectors.toList()));
		return propiedad;
	}

	@Override
	@Transactional
	public List<Propiedad> saveAll(List<Propiedad> propiedades) {
		List<Propiedad> resultado = new ArrayList<Propiedad>();
		for (Propiedad propiedad : propiedades) {
			if (null == propiedadDao.findByIdPropiedad(propiedad.getIdPropiedad())) {
				clientePlantaFeign.crear(propiedad.getPlantas());
				resultado.add(propiedadDao.save(propiedad));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Propiedad update(Propiedad propiedad, Long id) {
		Propiedad propiedadRepo = propiedadDao.findById(id).orElseThrow(EntityNotFoundException::new);
		propiedadRepo.setPropiedad(propiedad);
		return propiedadDao.save(propiedadRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		propiedadDao.deleteById(id);
	}

}
