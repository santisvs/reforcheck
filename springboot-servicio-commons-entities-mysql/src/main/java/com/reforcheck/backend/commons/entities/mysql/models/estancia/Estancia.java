package com.reforcheck.backend.commons.entities.mysql.models.estancia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commons.TresD;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.armario.Armario;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.banera.Banera;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.bidet.Bidet;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.climatizacion.Climatizacion;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.ducha.Ducha;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.iluminacion.Iluminacion;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.inodoro.Inodoro;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.instalacion.Instalacion;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.lavabo.Lavabo;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.mobiliarioobra.MobiliarioObra;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.pintura.Pintura;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.puerta.Puerta;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.radiador.Radiador;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.revestimiento.Revestimiento;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.rodapie.Rodapie;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.ventana.Ventana;
import com.reforcheck.backend.commons.entities.mysql.models.entidades.Solado;
import com.reforcheck.backend.commons.entities.mysql.models.entidades.Techo;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.SubtipoEstancia;
import com.reforcheck.backend.commons.entities.mysql.models.tipos.TipoEstancia;

@Entity
@Table(name = "estancias")
@Inheritance(strategy = InheritanceType.JOINED)
public class Estancia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_estancia")
	private String idEstancia;
	private String nombre;
	private TipoEstancia tipo;
	private SubtipoEstancia subtipo;
	@Embedded
	private TresD medida;
	@Embedded
	private Info info;
	@Embedded
	private Precio precio;
	@Transient
	private List<Ventana> ventanas;
	@Transient
	private List<Puerta> puertas;
	@Transient
	private List<Armario> armarios;
	@Transient
	private List<Radiador> radiadores;
	@Transient
	private List<Climatizacion> climatizaciones;
	@Transient
	private MobiliarioObra mobiliarioObra;
	@Transient
	private Techo techo;
	@Transient
	private Revestimiento revestimiento;
	@Transient
	private Solado solado;
	@Transient
	private Rodapie rodapie;
	@Transient
	private Pintura pintura;
	@Transient
	private Instalacion instalacion;
	@Transient
	private Iluminacion iluminacion;
	@Transient
	private List<Banera> baneras;
	@Transient
	private List<Ducha> duchas;
	@Transient
	private List<Lavabo> lavabos;
	@Transient
	private List<Inodoro> inodoros;
	@Transient
	private List<Bidet> bidets;

	public Estancia() {
		super();
		this.idEstancia = ConstantsTypes.STRING_EMPTY;
		this.nombre = ConstantsTypes.STRING_EMPTY;
		this.tipo = TipoEstancia.SIN_DEFINIR;
		this.subtipo = SubtipoEstancia.SIN_DEFINIR;
		this.medida = new TresD();
		this.ventanas = null;
		this.puertas = null;
		this.armarios = null;
		this.radiadores = null;
		this.climatizaciones = null;
		this.mobiliarioObra = null;
		this.techo = null;
		this.revestimiento = null;
		this.solado = null;
		this.rodapie = null;
		this.pintura = null;
		this.instalacion = null;
		this.iluminacion = null;
		this.baneras = null;
		this.duchas = null;
		this.lavabos = null;
		this.inodoros = null;
		this.bidets = null;
	}
	
	public void setEstancia(Estancia estancia) {
		this.idEstancia = estancia.idEstancia;
		this.nombre = estancia.nombre;
		this.tipo = estancia.tipo;
		this.subtipo = estancia.subtipo;
		this.medida = estancia.medida;
		setVentanas(estancia.getVentanas());
		setPuertas(estancia.getPuertas());
		setArmarios(estancia.getArmarios());
		setRadiadores(estancia.getRadiadores());
		setClimatizaciones(estancia.getClimatizaciones());
		setMobiliarioObra(estancia.getMobiliarioObra());
		setTecho(estancia.getTecho());
		setRevestimiento(estancia.getRevestimiento());
		setSolado(estancia.getSolado());
		setRodapie(estancia.getRodapie());
		setPintura(estancia.getPintura());
		setInstalacion(estancia.getInstalacion());
		setIluminacion(estancia.getIluminacion());
		setBaneras(estancia.getBaneras());
		setDuchas(estancia.getDuchas());
		setLavabos(estancia.getLavabos());
		setInodoros(estancia.getInodoros());
		setBidets(estancia.getBidets());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdEstancia() {
		return idEstancia;
	}

	public void setIdEstancia(String idEstancia) {
		this.idEstancia = idEstancia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoEstancia getTipo() {
		return tipo;
	}

	public void setTipo(TipoEstancia tipo) {
		this.tipo = tipo;
	}

	public SubtipoEstancia getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(SubtipoEstancia subtipo) {
		this.subtipo = subtipo;
	}

	public TresD getMedida() {
		return medida;
	}

	public void setMedida(TresD medida) {
		this.medida = medida;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public List<Ventana> getVentanas() {
		return ventanas;
	}

	public void setVentanas(List<Ventana> ventanas) {
		this.ventanas = ventanas;
	}

	public List<Puerta> getPuertas() {
		return puertas;
	}

	public void setPuertas(List<Puerta> puertas) {
		this.puertas = puertas;
	}

	public List<Armario> getArmarios() {
		return armarios;
	}

	public void setArmarios(List<Armario> armarios) {
		this.armarios = armarios;
	}

	public List<Radiador> getRadiadores() {
		return radiadores;
	}

	public void setRadiadores(List<Radiador> radiadores) {
		this.radiadores = radiadores;
	}

	public List<Climatizacion> getClimatizaciones() {
		return climatizaciones;
	}

	public void setClimatizaciones(List<Climatizacion> climatizaciones) {
		this.climatizaciones = climatizaciones;
	}

	public MobiliarioObra getMobiliarioObra() {
		return mobiliarioObra;
	}

	public void setMobiliarioObra(MobiliarioObra mobiliarioObra) {
		this.mobiliarioObra = mobiliarioObra;
	}

	public Techo getTecho() {
		return techo;
	}

	public void setTecho(Techo techo) {
		this.techo = techo;
	}

	public Revestimiento getRevestimiento() {
		return revestimiento;
	}

	public void setRevestimiento(Revestimiento revestimiento) {
		this.revestimiento = revestimiento;
	}

	public Solado getSolado() {
		return solado;
	}

	public void setSolado(Solado solado) {
		this.solado = solado;
	}

	public Rodapie getRodapie() {
		return rodapie;
	}

	public void setRodapie(Rodapie rodapie) {
		this.rodapie = rodapie;
	}

	public Pintura getPintura() {
		return pintura;
	}

	public void setPintura(Pintura pintura) {
		this.pintura = pintura;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public Iluminacion getIluminacion() {
		return iluminacion;
	}

	public void setIluminacion(Iluminacion iluminacion) {
		this.iluminacion = iluminacion;
	}

	public List<Banera> getBaneras() {
		return baneras;
	}

	public void setBaneras(List<Banera> baneras) {
		this.baneras = baneras;
	}

	public List<Ducha> getDuchas() {
		return duchas;
	}

	public void setDuchas(List<Ducha> duchas) {
		this.duchas = duchas;
	}

	public List<Lavabo> getLavabos() {
		return lavabos;
	}

	public void setLavabos(List<Lavabo> lavabos) {
		this.lavabos = lavabos;
	}

	public List<Inodoro> getInodoros() {
		return inodoros;
	}

	public void setInodoros(List<Inodoro> inodoros) {
		this.inodoros = inodoros;
	}

	public List<Bidet> getBidets() {
		return bidets;
	}

	public void setBidets(List<Bidet> bidets) {
		this.bidets = bidets;
	}

}
