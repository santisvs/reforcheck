package com.reforcheck.backend.plantas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.estancia.Estancia;
import com.reforcheck.backend.commons.entities.mysql.models.planta.Planta;
import com.reforcheck.backend.plantas.clients.EstanciaClientRest;
import com.reforcheck.backend.plantas.repositories.PlantaRepository;

@Service("serviceFeign")
public class PlantaServiceFeign implements PlantaService {

	@Autowired
	private PlantaRepository plantaDao;
	@Autowired
	private EstanciaClientRest clienteEstanciaFeign;
	
	@Override
	@Transactional(readOnly = true)
	public List<Planta> findAll() {

		ArrayList<Planta> plantasList = (ArrayList<Planta>) plantaDao.findAll();

		for (Planta planta : plantasList) {
			planta.setEstancias(clienteEstanciaFeign.listarByIdPlanta(planta.getIdPlanta()).stream()
					.map(p -> new Estancia(p)).collect(Collectors.toList()));
		}

		return plantasList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Planta> findAllByIdPlanta(List<String> referencias) {
		ArrayList<Planta> plantasList = new ArrayList<Planta>();
		for (String referencia : referencias) {
			plantasList.add(findByIdPlanta(referencia));
		}
		return plantasList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Planta> findAllByIdPropiedad(String idPropiedad) {
		return plantaDao.findByIdPropiedad(idPropiedad);
	}

	@Override
	@Transactional(readOnly = true)
	public Planta findById(Long id) {
		Planta planta = plantaDao.findById(id).orElseThrow(EntityNotFoundException::new);
		planta.setEstancias(clienteEstanciaFeign.listarByIdPlanta(planta.getIdPlanta()).stream()
				.map(p -> new Estancia(p)).collect(Collectors.toList()));
		return planta;
	}

	@Override
	@Transactional(readOnly = true)
	public Planta findByIdPlanta(String referencia) {
		Planta planta = plantaDao.findByIdPlanta(referencia);
		planta.setEstancias(clienteEstanciaFeign.listarByIdPlanta(planta.getIdPlanta()).stream()
				.map(p -> new Estancia(p)).collect(Collectors.toList()));
		return planta;
	}

	@Override
	@Transactional
	public List<Planta> saveAll(List<Planta> plantas) {
		List<Planta> resultado = new ArrayList<Planta>();
		for (Planta planta : plantas) {
			if (null == plantaDao.findByIdPlanta(planta.getIdPlanta())) {
				clienteEstanciaFeign.crear(planta.getEstancias());
				resultado.add(plantaDao.save(planta));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Planta update(Planta planta, Long id) {
		Planta plantaRepo = plantaDao.findById(id).orElseThrow(EntityNotFoundException::new);
		plantaRepo.setPlanta(planta);
		return plantaDao.save(plantaRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		plantaDao.deleteById(id);
	}

}
