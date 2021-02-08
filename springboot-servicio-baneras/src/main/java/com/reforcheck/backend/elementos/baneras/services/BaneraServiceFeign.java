package com.reforcheck.backend.elementos.baneras.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.banera.Banera;
import com.reforcheck.backend.elementos.baneras.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.baneras.repositories.BaneraRepository;

@Service("serviceFeign")
public class BaneraServiceFeign implements BaneraService {

	@Autowired
	private BaneraRepository baneraRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Banera> findAll() {
		ArrayList<Banera> banerasList = (ArrayList<Banera>) baneraRepository.findAll();

		for (Banera banera : banerasList) {
			banera.setFabricantes(clienteFabricanteFeign.listarByReferencia(banera.getReferenciasFabricantes()));
		}

		return banerasList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Banera> findAllByIdElem(List<String> idElems) {
		List<Banera> result = new ArrayList<Banera>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Banera> findAllByIdEstancia(String idEstancia) {
		List<Banera> baneras = baneraRepository.findByIdEstancia(idEstancia);
		for (Banera banera : baneras) {
			banera.setFabricantes(clienteFabricanteFeign.listarByReferencia(banera.getReferenciasFabricantes()));
		}
		return baneras;
	}

	@Override
	@Transactional(readOnly = true)
	public Banera findById(Long id) {
		Banera banera = baneraRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		banera.setFabricantes(clienteFabricanteFeign.listarByReferencia(banera.getReferenciasFabricantes()));
		return banera;
	}

	@Override
	@Transactional(readOnly = true)
	public Banera findByIdElem(String idElem) {
		Banera banera = baneraRepository.findByIdElem(idElem);
		banera.setFabricantes(clienteFabricanteFeign.listarByReferencia(banera.getReferenciasFabricantes()));
		return banera;
	}

	@Override
	@Transactional
	public List<Banera> saveAll(List<Banera> baneras) {
		List<Banera> resultado = new ArrayList<Banera>();
		for (Banera banera : baneras) {
			if (null == baneraRepository.findByIdElem(banera.getIdElem())) {
				clienteFabricanteFeign.crear(banera.getFabricantes());
				resultado.add(baneraRepository.save(banera));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Banera update(Banera banera, Long id) {
		Banera baneraRepo = baneraRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		baneraRepo.setBanera(banera);
		return baneraRepository.save(baneraRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		baneraRepository.deleteById(id);
	}
}
