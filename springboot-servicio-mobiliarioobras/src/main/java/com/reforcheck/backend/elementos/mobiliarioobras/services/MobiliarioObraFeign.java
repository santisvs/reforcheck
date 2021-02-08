package com.reforcheck.backend.elementos.mobiliarioobras.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.mobiliarioobra.MobiliarioObra;
import com.reforcheck.backend.elementos.mobiliarioobras.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.mobiliarioobras.repositories.MobiliarioObraRepository;

@Service("serviceFeign")
public class MobiliarioObraFeign implements MobiliarioObraService {

	@Autowired
	private MobiliarioObraRepository mobiliarioObraRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<MobiliarioObra> findAll() {
		ArrayList<MobiliarioObra> mobiliarioObraesList = (ArrayList<MobiliarioObra>) mobiliarioObraRepository.findAll();

		for (MobiliarioObra mobiliarioObra : mobiliarioObraesList) {
			mobiliarioObra
					.setFabricantes(clienteFabricanteFeign.listarByReferencia(mobiliarioObra.getReferenciasFabricantes()));
		}

		return mobiliarioObraesList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MobiliarioObra> findAllByIdElem(List<String> idElems) {
		List<MobiliarioObra> result = new ArrayList<MobiliarioObra>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public MobiliarioObra findByIdEstancia(String idEstancia) {
		MobiliarioObra mobiliarioObra = mobiliarioObraRepository.findByIdEstancia(idEstancia);
		mobiliarioObra.setFabricantes(clienteFabricanteFeign.listarByReferencia(mobiliarioObra.getReferenciasFabricantes()));
		
		return mobiliarioObra;
	}

	@Override
	@Transactional(readOnly = true)
	public MobiliarioObra findById(Long id) {
		MobiliarioObra mobiliarioObra = mobiliarioObraRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		mobiliarioObra.setFabricantes(clienteFabricanteFeign.listarByReferencia(mobiliarioObra.getReferenciasFabricantes()));
		return mobiliarioObra;
	}

	@Override
	@Transactional(readOnly = true)
	public MobiliarioObra findByIdElem(String idElem) {
		MobiliarioObra mobiliarioObra = mobiliarioObraRepository.findByIdElem(idElem);
		mobiliarioObra.setFabricantes(clienteFabricanteFeign.listarByReferencia(mobiliarioObra.getReferenciasFabricantes()));
		return mobiliarioObra;
	}

	@Override
	@Transactional
	public MobiliarioObra save(MobiliarioObra mobiliarioObra) {
		MobiliarioObra resultado = null;
		if (null == mobiliarioObraRepository.findByIdElem(mobiliarioObra.getIdElem())) {
			clienteFabricanteFeign.crear(mobiliarioObra.getFabricantes());
			resultado = mobiliarioObraRepository.save(mobiliarioObra);
		}
		return resultado;
	}

	@Override
	@Transactional
	public MobiliarioObra update(MobiliarioObra mobiliarioObra, Long id) {
		MobiliarioObra mobiliarioObraRepo = mobiliarioObraRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		mobiliarioObraRepo.setMobiliarioObra(mobiliarioObra);
		return mobiliarioObraRepository.save(mobiliarioObraRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		mobiliarioObraRepository.deleteById(id);
	}

}
