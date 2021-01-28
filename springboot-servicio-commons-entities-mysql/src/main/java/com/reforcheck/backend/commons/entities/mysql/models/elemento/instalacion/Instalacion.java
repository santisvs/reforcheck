package com.reforcheck.backend.commons.entities.mysql.models.elemento.instalacion;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commons.DosD;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commons.Precio;
import com.reforcheck.backend.commons.entities.mysql.models.elemento.instalacion.Instalacion;
import com.reforcheck.backend.commons.entities.mysql.models.fabricante.Fabricante;

/**
 * 
 * <b>Instalacion</b> <br>
 * Clase que define el elemento instalacion.
 * 
 * <ul>
 * <li>medida: medida 2D que tiene la instalación</li>
 * <li>cajaRegistro: número de cajas de registro</li>
 * <li>interruptoresConmutados: número de interruptores conmutados</li>
 * <li>interruptoresNoConmutados: número de interruptores no conmutados</li>
 * <li>enchufes: número de enchufes</li>
 * <li>tomaTv: número de tomas de Tv</li>
 * <li>tomaTelef: número de tomas de telefono</li>
 * <li>tomaDatos: número de tomas de datos</li>
 * <li>elemento: Información adicional de elemento</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 *
 */
@Entity
@Table(name = "instalaciones")
@PrimaryKeyJoinColumn(name = "elementoId")
public class Instalacion extends Elemento {

	private static final long serialVersionUID = 1L;

	@Embedded
	private DosD medida;
	@Column(name = "caja_registro")
	private Integer cajaRegistro;
	@Column(name = "interruptores_conmutados")
	private Integer interruptoresConmutados;
	@Column(name = "interruptores_no_conmutados")
	private Integer interruptoresNoConmutados;
	private Integer enchufes;
	@Column(name = "toma_tv")
	private Integer tomaTv;
	@Column(name = "toma_telef")
	private Integer tomaTelef;
	@Column(name = "toma_datos")
	private Integer tomaDatos;

	public Instalacion() {
		super();
		this.medida = new DosD();
		this.cajaRegistro = ConstantsTypes.ENT_0;
		this.interruptoresConmutados = ConstantsTypes.ENT_0;
		this.interruptoresNoConmutados = ConstantsTypes.ENT_0;
		this.enchufes = ConstantsTypes.ENT_0;
		this.tomaTv = ConstantsTypes.ENT_0;
		this.tomaTelef = ConstantsTypes.ENT_0;
		this.tomaDatos = ConstantsTypes.ENT_0;
	}

	public Instalacion(DosD medida, Integer cajaRegistro, Integer interruptoresConmutados,
			Integer interruptoresNoConmutados, Integer enchufes, Integer tomaTv, Integer tomaTelef, Integer tomaDatos,
			String idElem, String idEstancia, Info info, List<String> referenciasFabricantes,
			List<Fabricante> fabricantes, Precio precio) {
		super(idElem, idEstancia, info, referenciasFabricantes, fabricantes, precio);
		this.medida = medida;
		this.cajaRegistro = cajaRegistro;
		this.interruptoresConmutados = interruptoresConmutados;
		this.interruptoresNoConmutados = interruptoresNoConmutados;
		this.enchufes = enchufes;
		this.tomaTv = tomaTv;
		this.tomaTelef = tomaTelef;
		this.tomaDatos = tomaDatos;
	}

	public Instalacion(DosD medida, Integer cajaRegistro, Integer interruptoresConmutados,
			Integer interruptoresNoConmutados, Integer enchufes, Integer tomaTv, Integer tomaTelef, Integer tomaDatos,
			Elemento elemento) {
		setElemento(elemento);
		this.medida = medida;
		this.cajaRegistro = cajaRegistro;
		this.interruptoresConmutados = interruptoresConmutados;
		this.interruptoresNoConmutados = interruptoresNoConmutados;
		this.enchufes = enchufes;
		this.tomaTv = tomaTv;
		this.tomaTelef = tomaTelef;
		this.tomaDatos = tomaDatos;
	}
	
	public Instalacion(Instalacion instalacion) {
		setMedida(instalacion.getMedida());
		setCajaRegistro(instalacion.getCajaRegistro());
		setInterruptoresConmutados(instalacion.getInterruptoresConmutados());
		setInterruptoresNoConmutados(instalacion.getInterruptoresNoConmutados());
		setEnchufes(instalacion.getEnchufes());
		setTomaTv(instalacion.getTomaTv());
		setTomaTelef(instalacion.getTomaTelef());
		setTomaDatos(instalacion.getTomaDatos());
		setIdElem(instalacion.getIdElem());
		setIdEstancia(instalacion.getIdEstancia());
		setInfo(instalacion.getInfo());
		setReferenciasFabricantes(instalacion.getReferenciasFabricantes());
		setFabricantes(instalacion.getFabricantes());
		setPrecio(instalacion.getPrecio());
	}
	
	public void setInstalacion(Instalacion instalacion) {
		setMedida(instalacion.getMedida());
		setCajaRegistro(instalacion.getCajaRegistro());
		setInterruptoresConmutados(instalacion.getInterruptoresConmutados());
		setInterruptoresNoConmutados(instalacion.getInterruptoresNoConmutados());
		setEnchufes(instalacion.getEnchufes());
		setTomaTv(instalacion.getTomaTv());
		setTomaTelef(instalacion.getTomaTelef());
		setTomaDatos(instalacion.getTomaDatos());
		setIdElem(instalacion.getIdElem());
		setIdEstancia(instalacion.getIdEstancia());
		setInfo(instalacion.getInfo());
		setReferenciasFabricantes(instalacion.getReferenciasFabricantes());
		setFabricantes(instalacion.getFabricantes());
		setPrecio(instalacion.getPrecio());
	}

	public DosD getMedida() {
		return medida;
	}

	public void setMedida(DosD medida) {
		this.medida = medida;
	}

	public Integer getCajaRegistro() {
		return cajaRegistro;
	}

	public void setCajaRegistro(Integer cajaRegistro) {
		this.cajaRegistro = cajaRegistro;
	}

	public Integer getInterruptoresConmutados() {
		return interruptoresConmutados;
	}

	public void setInterruptoresConmutados(Integer interruptoresConmutados) {
		this.interruptoresConmutados = interruptoresConmutados;
	}

	public Integer getInterruptoresNoConmutados() {
		return interruptoresNoConmutados;
	}

	public void setInterruptoresNoConmutados(Integer interruptoresNoConmutados) {
		this.interruptoresNoConmutados = interruptoresNoConmutados;
	}

	public Integer getEnchufes() {
		return enchufes;
	}

	public void setEnchufes(Integer enchufes) {
		this.enchufes = enchufes;
	}

	public Integer getTomaTv() {
		return tomaTv;
	}

	public void setTomaTv(Integer tomaTv) {
		this.tomaTv = tomaTv;
	}

	public Integer getTomaTelef() {
		return tomaTelef;
	}

	public void setTomaTelef(Integer tomaTelef) {
		this.tomaTelef = tomaTelef;
	}

	public Integer getTomaDatos() {
		return tomaDatos;
	}

	public void setTomaDatos(Integer tomaDatos) {
		this.tomaDatos = tomaDatos;
	}

}
