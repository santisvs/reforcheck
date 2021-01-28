package com.reforcheck.backend.estancias.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reforcheck.backend.commons.entities.mysql.models.elemento.armario.Armario;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.banera.Banera;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.bidet.Bidet;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.climatizacion.Climatizacion;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.ducha.Ducha;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.inodoro.Inodoro;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Estancia;
import com.reforcheck.backend.estancias.clients.ArmarioClientRest;
import com.reforcheck.backend.estancias.clients.BaneraClientRest;
import com.reforcheck.backend.estancias.clients.BidetClientRest;
import com.reforcheck.backend.estancias.clients.ClimatizacionClientRest;
import com.reforcheck.backend.estancias.clients.DuchaClientRest;
import com.reforcheck.backend.estancias.clients.IluminacionClientRest;
import com.reforcheck.backend.estancias.clients.InodoroClientRest;
import com.reforcheck.backend.estancias.repositories.EstanciaRepository;

@Service("serviceFeign")
public class EstanciaServiceFeign implements EstanciaService {

	@Autowired
	private EstanciaRepository estanciaDao;
	@Autowired
	private ArmarioClientRest clienteArmarioFeign;
	@Autowired
	private BaneraClientRest clienteBaneraFeign;
	@Autowired
	private BidetClientRest clienteBidetFeign;
	@Autowired
	private DuchaClientRest clienteDuchaFeign;
	@Autowired
	private InodoroClientRest clienteInodoroFeign;
	@Autowired
	private ClimatizacionClientRest clienteClimatizacionFeign;
	@Autowired
	private IluminacionClientRest clienteIluminacionFeign;

	@Override
	@Transactional(readOnly = true)
	public List<Estancia> findAll() {

		ArrayList<Estancia> estanciasList = (ArrayList<Estancia>) estanciaDao.findAll();

		for (Estancia estancia : estanciasList) {
			setElementosByIdEstancia(estancia);
		}

		return estanciasList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estancia> findAllByIdEstancia(List<String> referencias) {
		ArrayList<Estancia> estanciasList = new ArrayList<Estancia>();
		for (String referencia : referencias) {
			estanciasList.add(findByIdEstancia(referencia));
		}
		return estanciasList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estancia> findAllByIdPlanta(String idPlanta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Estancia findById(Long id) {
		Estancia estancia = estanciaDao.findById(id).orElseThrow(EntityNotFoundException::new);
		setElementosByIdEstancia(estancia);

		return estancia;
	}

	@Override
	@Transactional(readOnly = true)
	public Estancia findByIdEstancia(String referencia) {
		Estancia estancia = estanciaDao.findByIdEstancia(referencia);
		setElementosByIdEstancia(estancia);
		return estancia;
	}

	@Override
	@Transactional
	public List<Estancia> saveAll(List<Estancia> estancias) {
		List<Estancia> resultado = new ArrayList<Estancia>();
		for (Estancia estancia : estancias) {
			if (null == estanciaDao.findByIdEstancia(estancia.getIdEstancia())) {
				clienteArmarioFeign.crear(estancia.getArmarios());
				clienteBaneraFeign.crear(estancia.getBaneras());
				clienteBidetFeign.crear(estancia.getBidets());
				clienteInodoroFeign.crear(estancia.getInodoros());
				clienteDuchaFeign.crear(estancia.getDuchas());
				clienteClimatizacionFeign.crear(estancia.getClimatizaciones());
				clienteIluminacionFeign.crear(estancia.getIluminacion());
				resultado.add(estanciaDao.save(estancia));
			}
		}
		return resultado;
	}

	@Override
	@Transactional
	public Estancia update(Estancia estancia, Long id) {
		Estancia estanciaRepo = estanciaDao.findById(id).orElseThrow(EntityNotFoundException::new);
		estanciaRepo.setEstancia(estancia);
		return estanciaDao.save(estanciaRepo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		estanciaDao.deleteById(id);
	}

	/*
	 * Método privado de llamada a cada microservicio específico de cada elemento
	 */
	private void setElementosByIdEstancia(Estancia estancia) {
		estancia.setArmarios(clienteArmarioFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Armario(p)).collect(Collectors.toList()));
		estancia.setBaneras(clienteBaneraFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Banera(p)).collect(Collectors.toList()));
		estancia.setBidets(clienteBidetFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Bidet(p)).collect(Collectors.toList()));
		estancia.setInodoros(clienteInodoroFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Inodoro(p)).collect(Collectors.toList()));
		estancia.setDuchas(clienteDuchaFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Ducha(p)).collect(Collectors.toList()));
		estancia.setClimatizaciones(clienteClimatizacionFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Climatizacion(p)).collect(Collectors.toList()));
		estancia.setIluminacion(clienteIluminacionFeign.listarByIdEstancia(estancia.getIdEstancia()));
	}

}
