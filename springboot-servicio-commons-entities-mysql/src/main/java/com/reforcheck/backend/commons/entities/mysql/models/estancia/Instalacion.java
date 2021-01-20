package com.reforcheck.backend.commons.entities.mysql.models.estancia;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.reforcheck.backend.commons.constants.ConstantsTypes;
import com.reforcheck.backend.commons.entities.mysql.models.commos.DosD;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Elemento;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Fabricante;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Info;
import com.reforcheck.backend.commons.entities.mysql.models.commos.Precio;

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
			String idElem, Info info, Fabricante fabricante, Precio precio) {
		super(idElem, info, fabricante, precio);
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
