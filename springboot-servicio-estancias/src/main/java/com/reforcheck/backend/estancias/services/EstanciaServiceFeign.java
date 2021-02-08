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
import com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo.Lavabo;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.puerta.Puerta;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.radiador.Radiador;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.ventana.Ventana;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Estancia;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.TipoEstancia;
import com.reforcheck.backend.estancias.clients.ArmarioClientRest;
import com.reforcheck.backend.estancias.clients.BaneraClientRest;
import com.reforcheck.backend.estancias.clients.BidetClientRest;
import com.reforcheck.backend.estancias.clients.ClimatizacionClientRest;
import com.reforcheck.backend.estancias.clients.DuchaClientRest;
import com.reforcheck.backend.estancias.clients.IluminacionClientRest;
import com.reforcheck.backend.estancias.clients.InodoroClientRest;
import com.reforcheck.backend.estancias.clients.InstalacionClientRest;
import com.reforcheck.backend.estancias.clients.LavaboClientRest;
import com.reforcheck.backend.estancias.clients.MobiliarioObraClientRest;
import com.reforcheck.backend.estancias.clients.PinturaClientRest;
import com.reforcheck.backend.estancias.clients.PuertaClientRest;
import com.reforcheck.backend.estancias.clients.RadiadorClientRest;
import com.reforcheck.backend.estancias.clients.RevestimientoClientRest;
import com.reforcheck.backend.estancias.clients.RodapieClientRest;
import com.reforcheck.backend.estancias.clients.SoladoClientRest;
import com.reforcheck.backend.estancias.clients.TechoClientRest;
import com.reforcheck.backend.estancias.clients.VentanaClientRest;
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
	private LavaboClientRest clienteLavaboFeign;
	@Autowired
	private VentanaClientRest clienteVentanaFeign;
	@Autowired
	private PuertaClientRest clientePuertaFeign;
	@Autowired
	private RadiadorClientRest clienteRadiadorFeign;
	@Autowired
	private ClimatizacionClientRest clienteClimatizacionFeign;
	@Autowired
	private IluminacionClientRest clienteIluminacionFeign;
	@Autowired
	private InstalacionClientRest clienteInstalacionFeign;
	@Autowired
	private MobiliarioObraClientRest clienteMobiliarioObraFeign;
	@Autowired
	private PinturaClientRest clientePinturaFeign;
	@Autowired
	private RevestimientoClientRest clienteRevestimientoFeign;
	@Autowired
	private RodapieClientRest clienteRodapieFeign;
	@Autowired
	private SoladoClientRest clienteSoladoFeign;
	@Autowired
	private TechoClientRest clienteTechoFeign;

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
		return estanciaDao.findByIdPlanta(idPlanta);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Estancia> findAllByIdPropiedad(String idPropiedad){
		return estanciaDao.findByIdPropiedad(idPropiedad);
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
				if(TipoEstancia.HUMEDA.equals(estancia.getTipo())) {
					if(!estancia.getBaneras().isEmpty()) {
						clienteBaneraFeign.crear(estancia.getBaneras());
					}
					if(!estancia.getBidets().isEmpty()) {
						clienteBidetFeign.crear(estancia.getBidets());
					}
					if(!estancia.getInodoros().isEmpty()) {
						clienteInodoroFeign.crear(estancia.getInodoros());
					}
					if(!estancia.getLavabos().isEmpty()) {
						clienteLavaboFeign.crear(estancia.getLavabos());
					}
					if(!estancia.getDuchas().isEmpty()) {
						clienteDuchaFeign.crear(estancia.getDuchas());
					}
				}
				if(!estancia.getArmarios().isEmpty()) {
					clienteArmarioFeign.crear(estancia.getArmarios());
				}
				if(!estancia.getClimatizaciones().isEmpty()) {
					clienteClimatizacionFeign.crear(estancia.getClimatizaciones());
				}
				if(!estancia.getVentanas().isEmpty()) {
					clienteVentanaFeign.crear(estancia.getVentanas());
				}
				if(!estancia.getPuertas().isEmpty()) {
					clientePuertaFeign.crear(estancia.getPuertas());
				}
				if(!estancia.getRadiadores().isEmpty()) {
					clienteRadiadorFeign.crear(estancia.getRadiadores());
				}
				if(estancia.getIluminacion()==null) {
					clienteIluminacionFeign.crear(estancia.getIluminacion());
				}
				if(estancia.getMobiliarioObra()==null) {
					clienteMobiliarioObraFeign.crear(estancia.getMobiliarioObra());
				}
				if(estancia.getPintura()==null) {
					clientePinturaFeign.crear(estancia.getPintura());
				}
				if(estancia.getRevestimiento()==null) {
					clienteRevestimientoFeign.crear(estancia.getRevestimiento());
				}
				if(estancia.getRodapie()==null) {
					clienteRodapieFeign.crear(estancia.getRodapie());
				}
				if(estancia.getSolado()==null) {
					clienteSoladoFeign.crear(estancia.getSolado());
				}
				if(estancia.getTecho()==null) {
					clienteTechoFeign.crear(estancia.getTecho());
				}	
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
		if(TipoEstancia.HUMEDA.equals(estancia.getTipo())) {
			estancia.setBaneras(clienteBaneraFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
					.map(p -> new Banera(p)).collect(Collectors.toList()));
			estancia.setBidets(clienteBidetFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
					.map(p -> new Bidet(p)).collect(Collectors.toList()));
			estancia.setInodoros(clienteInodoroFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
					.map(p -> new Inodoro(p)).collect(Collectors.toList()));
			estancia.setLavabos(clienteLavaboFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
					.map(p -> new Lavabo(p)).collect(Collectors.toList()));
			estancia.setDuchas(clienteDuchaFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
					.map(p -> new Ducha(p)).collect(Collectors.toList()));
		}
		estancia.setArmarios(clienteArmarioFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Armario(p)).collect(Collectors.toList()));
		estancia.setClimatizaciones(clienteClimatizacionFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Climatizacion(p)).collect(Collectors.toList()));
		estancia.setVentanas(clienteVentanaFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Ventana(p)).collect(Collectors.toList()));
		estancia.setPuertas(clientePuertaFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Puerta(p)).collect(Collectors.toList()));
		estancia.setRadiadores(clienteRadiadorFeign.listarByIdEstancia(estancia.getIdEstancia()).stream()
				.map(p -> new Radiador(p)).collect(Collectors.toList()));
		estancia.setIluminacion(clienteIluminacionFeign.listarByIdEstancia(estancia.getIdEstancia()));
		estancia.setInstalacion(clienteInstalacionFeign.listarByIdEstancia(estancia.getIdEstancia()));
		estancia.setMobiliarioObra(clienteMobiliarioObraFeign.listarByIdEstancia(estancia.getIdEstancia()));
		estancia.setPintura(clientePinturaFeign.listarByIdEstancia(estancia.getIdEstancia()));
		estancia.setRevestimiento(clienteRevestimientoFeign.listarByIdEstancia(estancia.getIdEstancia()));
		estancia.setRodapie(clienteRodapieFeign.listarByIdEstancia(estancia.getIdEstancia()));
		estancia.setSolado(clienteSoladoFeign.listarByIdEstancia(estancia.getIdEstancia()));
		estancia.setTecho(clienteTechoFeign.listarByIdEstancia(estancia.getIdEstancia()));
		
	}

}
