package com.reforcheck.backend.elementos.ventanas.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.ventana.Ventana;
import com.reforcheck.backend.elementos.ventanas.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.ventanas.repositories.VentanaRepository;

@Service("serviceFeign")
public class VentanaServiceFeign implements VentanaService {

	@Autowired
	private VentanaRepository ventanaRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Ventana> findAll() {
		ArrayList<Ventana> ventanasList = (ArrayList<Ventana>) ventanaRepository.findAll();

		for (Ventana ventana : ventanasList) {
			ventana.setFabricantes(clienteFabricanteFeign.listarByReferencia(ventana.getReferenciasFabricantes()));
		}

		return ventanasList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ventana> findAllByIdElem(List<String> idElems) {
		List<Ventana> result = new ArrayList<Ventana>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ventana> findAllByIdEstancia(String idEstancia) {
		List<Ventana> ventanas = ventanaRepository.findByIdEstancia(idEstancia);
		for (Ventana ventana : ventanas) {
			ventana.setFabricantes(clienteFabricanteFeign.listarByReferencia(ventana.getReferenciasFabricantes()));
		}
		return ventanas;
	}

	@Override
	@Transactional(readOnly = true)
	public Ventana findById(Long id) {
		Ventana ventana = ventanaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		ventana.setFabricantes(clienteFabricanteFeign.listarByReferencia(ventana.getReferenciasFabricantes()));
		return ventana;
	}

	@Override
	@Transactional(readOnly = true)
	public Ventana findByIdElem(String idElem) {
		Ventana ventana = ventanaRepository.findByIdElem(idElem);
		ventana.setFabricantes(clienteFabricanteFeign.listarByReferencia(ventana.getReferenciasFabricantes()));
		return ventana;
	}

	@Override
	@Transactional
	public List<Ventana> saveAll(List<Ventana> ventanas) {
		List<Ventana> resultado = new ArrayList<Ventana>();
		for (Ventana ventana : ventanas) {
			if (null == ventanaRepository.findByIdElem(ventana.getIdElem())) {
				clienteFabricanteFeign.crear(ventana.getFabricantes());
				resultado.add(ventanaRepository.save(ventana));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Ventana update(Ventana ventana, Long id) {
		Ventana ventanaRepo = ventanaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		ventanaRepo.setVentana(ventana);
		return ventanaRepository.save(ventanaRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ventanaRepository.deleteById(id);
	}

}
