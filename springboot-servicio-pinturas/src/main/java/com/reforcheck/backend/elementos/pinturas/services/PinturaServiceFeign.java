package com.reforcheck.backend.elementos.pinturas.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.pintura.Pintura;
import com.reforcheck.backend.elementos.pinturas.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.pinturas.repositories.PinturaRepository;

@Service("serviceFeign")
public class PinturaServiceFeign implements PinturaService {

	@Autowired
	private PinturaRepository pinturaRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Pintura> findAll() {
		ArrayList<Pintura> pinturaesList = (ArrayList<Pintura>) pinturaRepository.findAll();

		for (Pintura pintura : pinturaesList) {
			pintura
					.setFabricantes(clienteFabricanteFeign.listarByReferencia(pintura.getReferenciasFabricantes()));
		}

		return pinturaesList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pintura> findAllByIdElem(List<String> idElems) {
		List<Pintura> result = new ArrayList<Pintura>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Pintura findByIdEstancia(String idEstancia) {
		Pintura pintura = pinturaRepository.findByIdEstancia(idEstancia);
		pintura.setFabricantes(clienteFabricanteFeign.listarByReferencia(pintura.getReferenciasFabricantes()));

		return pintura;
	}

	@Override
	@Transactional(readOnly = true)
	public Pintura findById(Long id) {
		Pintura pintura = pinturaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		pintura.setFabricantes(clienteFabricanteFeign.listarByReferencia(pintura.getReferenciasFabricantes()));
		return pintura;
	}

	@Override
	@Transactional(readOnly = true)
	public Pintura findByIdElem(String idElem) {
		Pintura pintura = pinturaRepository.findByIdElem(idElem);
		pintura.setFabricantes(clienteFabricanteFeign.listarByReferencia(pintura.getReferenciasFabricantes()));
		return pintura;
	}

	@Override
	@Transactional
	public Pintura save(Pintura pintura) {
		Pintura resultado = null;
		if (null == pinturaRepository.findByIdElem(pintura.getIdElem())) {
			clienteFabricanteFeign.crear(pintura.getFabricantes());
			resultado = pinturaRepository.save(pintura);
		}
		return resultado;
	}

	@Override
	@Transactional
	public Pintura update(Pintura pintura, Long id) {
		Pintura pinturaRepo = pinturaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		pinturaRepo.setPintura(pintura);
		return pinturaRepository.save(pinturaRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		pinturaRepository.deleteById(id);
	}

}
