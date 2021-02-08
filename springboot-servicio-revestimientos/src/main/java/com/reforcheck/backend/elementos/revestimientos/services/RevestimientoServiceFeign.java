package com.reforcheck.backend.elementos.revestimientos.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.revestimiento.Revestimiento;
import com.reforcheck.backend.elementos.revestimientos.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.revestimientos.repositories.RevestimientoRepository;

@Service("serviceFeign")
public class RevestimientoServiceFeign implements RevestimientoService {

	@Autowired
	private RevestimientoRepository revestimientoRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Revestimiento> findAll() {
		ArrayList<Revestimiento> revestimientoesList = (ArrayList<Revestimiento>) revestimientoRepository.findAll();

		for (Revestimiento revestimiento : revestimientoesList) {
			revestimiento
					.setFabricantes(clienteFabricanteFeign.listarByReferencia(revestimiento.getReferenciasFabricantes()));
		}

		return revestimientoesList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Revestimiento> findAllByIdElem(List<String> idElems) {
		List<Revestimiento> result = new ArrayList<Revestimiento>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Revestimiento findByIdEstancia(String idEstancia) {
		Revestimiento revestimiento = revestimientoRepository.findByIdEstancia(idEstancia);
		revestimiento.setFabricantes(clienteFabricanteFeign.listarByReferencia(revestimiento.getReferenciasFabricantes()));

		return revestimiento;
	}

	@Override
	@Transactional(readOnly = true)
	public Revestimiento findById(Long id) {
		Revestimiento revestimiento = revestimientoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		revestimiento.setFabricantes(clienteFabricanteFeign.listarByReferencia(revestimiento.getReferenciasFabricantes()));
		return revestimiento;
	}

	@Override
	@Transactional(readOnly = true)
	public Revestimiento findByIdElem(String idElem) {
		Revestimiento revestimiento = revestimientoRepository.findByIdElem(idElem);
		revestimiento.setFabricantes(clienteFabricanteFeign.listarByReferencia(revestimiento.getReferenciasFabricantes()));
		return revestimiento;
	}

	@Override
	@Transactional
	public Revestimiento save(Revestimiento revestimiento) {
		Revestimiento resultado = null;
		if (null == revestimientoRepository.findByIdElem(revestimiento.getIdElem())) {
			clienteFabricanteFeign.crear(revestimiento.getFabricantes());
			resultado = revestimientoRepository.save(revestimiento);
		}
		return resultado;
	}

	@Override
	@Transactional
	public Revestimiento update(Revestimiento revestimiento, Long id) {
		Revestimiento revestimientoRepo = revestimientoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		revestimientoRepo.setRevestimiento(revestimiento);
		return revestimientoRepository.save(revestimientoRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		revestimientoRepository.deleteById(id);
	}

}
