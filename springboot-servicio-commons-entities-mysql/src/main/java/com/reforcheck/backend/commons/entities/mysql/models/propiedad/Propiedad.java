package com.reforcheck.backend.commons.entities.mysql.models.propiedad;

import java.io.Serializable;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.planta.Planta;

/**
 * 
 * <b>Propiedad</b> <br>
 * Clase que las características de una propiedad perteneciente a un usuario.
 * 
 * <ul>
 * <li>id: identificación de bbdd</li>
 * <li>idPropiedad: identificación de una planta en el sistema</li>
 * <li>nombre_propietario: Nombre de la planta</li>
 * <li>numero: Número de planta</li>
 * <li>medida: Tamaño de la planta</li>
 * <li>info: Información adicional de la planta</li>
 * <li>precio: Coste de la reforma de esa planta</li>
 * <li>estancias: Lista de estancias que tiene la planta</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "plantas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Propiedad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_propiedad")
	private String idPropiedad;
	@Column(name = "id_propietario")
	private String idPropietario;
	private String direccion;
	private String tipo;
	private String gps;
	@Column(name = "fecha_construccion")
	private Date fechaConstruccion;
	@Column(name = "fecha_ultima_reforma")
	private Date fechaUltimaReforma;
	@Column(name = "id_reforchecker")
	private String idReforchecker;
	@Transient
	private List<Planta> plantas;

	public Propiedad() {
		super();
		this.idPropiedad = ConstantsTypes.STRING_EMPTY;
		this.idPropietario = ConstantsTypes.STRING_EMPTY;
		this.direccion = ConstantsTypes.STRING_EMPTY;
		this.tipo = ConstantsTypes.STRING_EMPTY;
		this.gps = ConstantsTypes.STRING_EMPTY;
		GregorianCalendar calendar = new GregorianCalendar(1900, 0, 1);
		this.fechaConstruccion = new Date(calendar.getTimeInMillis());
		this.fechaUltimaReforma = new Date(calendar.getTimeInMillis());
		this.idReforchecker = ConstantsTypes.STRING_EMPTY;
		this.plantas = null;
	}

	public Propiedad(String idPropiedad, String idPropietario, String direccion, String tipo, String gps,
			Date fechaConstruccion, Date fechaUltimaReforma, String idReforchecker, List<Planta> plantas) {
		super();
		setIdPropiedad(idPropiedad);
		setIdPropietario(idPropietario);
		setDireccion(direccion);
		setTipo(tipo);
		setGps(gps);
		setFechaConstruccion(fechaConstruccion);
		setFechaUltimaReforma(fechaUltimaReforma);
		setIdReforchecker(idReforchecker);
		setPlantas(plantas);
	}

	public void setPropiedad(Propiedad propiedad) {
		setIdPropiedad(idPropiedad);
		setIdPropietario(idPropietario);
		setDireccion(direccion);
		setTipo(tipo);
		setGps(gps);
		setFechaConstruccion(fechaConstruccion);
		setFechaUltimaReforma(fechaUltimaReforma);
		setIdReforchecker(idReforchecker);
		setPlantas(plantas);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(String idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public String getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(String idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public Date getFechaConstruccion() {
		return fechaConstruccion;
	}

	public void setFechaConstruccion(Date fechaConstruccion) {
		this.fechaConstruccion = fechaConstruccion;
	}

	public Date getFechaUltimaReforma() {
		return fechaUltimaReforma;
	}

	public void setFechaUltimaReforma(Date fechaUltimaReforma) {
		this.fechaUltimaReforma = fechaUltimaReforma;
	}

	public String getIdReforchecker() {
		return idReforchecker;
	}

	public void setIdReforchecker(String idReforchecker) {
		this.idReforchecker = idReforchecker;
	}

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

}
