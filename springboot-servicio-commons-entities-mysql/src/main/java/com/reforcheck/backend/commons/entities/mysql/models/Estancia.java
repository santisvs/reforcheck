package com.reforcheck.backend.commons.entities.mysql.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.commos.TresD;
import com.reforcheck.backend.commons.entities.mysql.models.commos.UnoD;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Armario;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Banera;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Bidet;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Climatizacion;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Ducha;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Iluminacion;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Inodoro;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Instalacion;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Lavabo;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.MobiliarioObra;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Pintura;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Puerta;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Radiador;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Revestimiento;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Rodapie;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Solado;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Techo;
import com.reforcheck.backend.commons.entities.mysql.models.estancia.Ventana;
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
	@Column(name = "id_elem")
	private String idElem;
	private String nombre;
	private TipoEstancia tipo;
	private SubtipoEstancia subtipo;
	@Embedded
	private TresD medida;
	@Embedded
	private Info info;
	@Embedded
	private Precio precio;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Ventana.class)
	private List<Ventana> ventanas;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Puerta.class)
	private List<Puerta> puertas;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Armario.class)
	private List<Armario> armarios;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Radiador.class)
	private List<Radiador> radiadores;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Climatizacion.class)
	private List<Climatizacion> climatizaciones;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = MobiliarioObra.class)
	@PrimaryKeyJoinColumn
	private MobiliarioObra mobiliarioObra;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Techo.class)
	@PrimaryKeyJoinColumn
	private Techo techo;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Revestimiento.class)
	@PrimaryKeyJoinColumn
	private Revestimiento revestimiento;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Solado.class)
	@PrimaryKeyJoinColumn
	private Solado solado;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Rodapie.class)
	@PrimaryKeyJoinColumn
	private Rodapie rodapie;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Pintura.class)
	@PrimaryKeyJoinColumn
	private Pintura pintura;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Instalacion.class)
	@PrimaryKeyJoinColumn
	private Instalacion instalacion;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Iluminacion.class)
	@PrimaryKeyJoinColumn
	private Iluminacion iluminacion;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Banera.class)
	private List<Banera> baneras;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Ducha.class)
	private List<Ducha> duchas;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Lavabo.class)
	private List<Lavabo> lavabos;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Inodoro.class)
	private List<Inodoro> inodoros;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Bidet.class)
	private List<Bidet> bidets;

	public Estancia() {
		super();
		this.idElem = ConstantsTypes.STRING_EMPTY;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdElem() {
		return idElem;
	}

	public void setIdElem(String idElem) {
		this.idElem = idElem;
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
