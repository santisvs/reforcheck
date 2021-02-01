package com.reforcheck.backend.elementos.solados.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.solado.Solado;
import com.reforcheck.backend.elementos.solados.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.solados.repositories.SoladoRepository;

@Service("serviceFeign")
public class SoladoServiceFeign implements SoladoService {

	@Autowired
	private SoladoRepository soladoRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Solado> findAll() {
		ArrayList<Solado> soladoesList = (ArrayList<Solado>) soladoRepository.findAll();

		for (Solado solado : soladoesList) {
			solado
					.setFabricantes(clienteFabricanteFeign.listarByReferencia(solado.getReferenciasFabricantes()));
		}

		return soladoesList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Solado> findAllByIdElem(List<String> idElems) {
		List<Solado> result = new ArrayList<Solado>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Solado findByIdEstancia(String idEstancia) {
		Solado solado = soladoRepository.findByIdEstancia(idEstancia);
		solado.setFabricantes(clienteFabricanteFeign.listarByReferencia(solado.getReferenciasFabricantes()));

		return solado;
	}

	@Override
	@Transactional(readOnly = true)
	public Solado findById(Long id) {
		Solado solado = soladoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		solado.setFabricantes(clienteFabricanteFeign.listarByReferencia(solado.getReferenciasFabricantes()));
		return solado;
	}

	@Override
	@Transactional(readOnly = true)
	public Solado findByIdElem(String idElem) {
		Solado solado = soladoRepository.findByIdElem(idElem);
		solado.setFabricantes(clienteFabricanteFeign.listarByReferencia(solado.getReferenciasFabricantes()));
		return solado;
	}

	@Override
	@Transactional
	public Solado save(Solado solado) {
		Solado resultado = null;
		if (null == soladoRepository.findByIdElem(solado.getIdElem())) {
			clienteFabricanteFeign.crear(solado.getFabricantes());
			resultado = soladoRepository.save(solado);
		}
		return resultado;
	}

	@Override
	@Transactional
	public Solado update(Solado solado, Long id) {
		Solado soladoRepo = soladoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		soladoRepo.setSolado(solado);
		return soladoRepository.save(soladoRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		soladoRepository.deleteById(id);
	}

}
