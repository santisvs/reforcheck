package com.practi.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.practi.app.commons.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long> {

}
