package com.reforcheck.backend.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.reforcheck.backend.commons.entities.mysql.models.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long> {

}
