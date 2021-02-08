package com.reforcheck.backend.elementos.armarios.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.armario.Armario;
import com.reforcheck.backend.elementos.armarios.clients.FabricanteClientRest;
import com.reforcheck.backend.elementos.armarios.repositories.ArmarioRepository;

@Service("serviceFeign")
public class ArmarioServiceFeign implements ArmarioService {

	@Autowired
	private ArmarioRepository armarioRepository;
	@Autowired
	private FabricanteClientRest clienteFabricanteFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Armario> findAll() {
		ArrayList<Armario> armariosList = (ArrayList<Armario>) armarioRepository.findAll();

		for (Armario armario : armariosList) {
			armario.setFabricantes(clienteFabricanteFeign.listarByReferencia(armario.getReferenciasFabricantes()));
		}

		return armariosList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Armario> findAllByIdElem(List<String> idElems) {
		List<Armario> result = new ArrayList<Armario>();
		for (String idElem : idElems) {
			result.add(this.findByIdElem(idElem));
		}
		return result;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Armario> findAllByIdEstancia(String idEstancia) {
		List<Armario> armarios = armarioRepository.findByIdEstancia(idEstancia);
		for (Armario armario : armarios) {
			armario.setFabricantes(clienteFabricanteFeign.listarByReferencia(armario.getReferenciasFabricantes()));
		}
		return armarios;
	}

	@Override
	@Transactional(readOnly = true)
	public Armario findById(Long id) {
		Armario armario = armarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		armario.setFabricantes(clienteFabricanteFeign.listarByReferencia(armario.getReferenciasFabricantes()));
		return armario;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Armario findByIdElem(String idElem) {
		Armario armario = armarioRepository.findByIdElem(idElem);
		armario.setFabricantes(clienteFabricanteFeign.listarByReferencia(armario.getReferenciasFabricantes()));
		return armario;
	}

	@Override
	@Transactional
	public List<Armario> saveAll(List<Armario> armarios) {
		List<Armario> resultado = new ArrayList<Armario>();
		for (Armario armario : armarios) {
			if(null == armarioRepository.findByIdElem(armario.getIdElem())) {
				clienteFabricanteFeign.crear(armario.getFabricantes());
				resultado.add(armarioRepository.save(armario));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Armario update(Armario armario, Long id) {
		Armario armarioRepo = armarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		armarioRepo.setArmario(armario);
		return armarioRepository.save(armarioRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		armarioRepository.deleteById(id);
	}

}
