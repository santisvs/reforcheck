package com.practi.app.commons.models.entity;

/**
 * <b>Item</b> <br>
 * Clase que define un {@link com.practi.app.commons.models.entity.Producto}
 * pero con más atributos.
 * 
 * <ul>
 * <li>{@link com.practi.app.commons.models.entity.Producto}</li>
 * <li>cantidad: Cantidad de ese producto</li>
 * </ul>
 * 
 * @author CTO Reforcheck - Santiago Vallejo <s.vallejo@reforcheck.com>
 * 
 */
public class Item {

	private Producto producto;
	private Integer cantidad;

	public Item() {
	}

	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}

}
