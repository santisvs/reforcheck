package com.reforcheck.backend.item.models.service;

import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.Item;
import com.reforcheck.backend.commons.entities.mysql.models.Producto;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);

	public Producto save(Producto producto);

	public Producto update(Producto producto, Long id);

	public void delete(Long id);
}
