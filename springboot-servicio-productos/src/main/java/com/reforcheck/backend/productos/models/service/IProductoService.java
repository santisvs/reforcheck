package com.reforcheck.backend.productos.models.service;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.Producto;

public interface IProductoService {

	public List<Producto> findAll();

	public Producto findById(Long id);

	public Producto save(Producto producto);

	public void deleteById(Long id);
}
