package com.unla.grupo1.services;

import java.util.List;

import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.models.ProductoModel;


public interface IProductoService {
	
	public List<Producto> getAll();
	public ProductoModel insertOrUpdate(ProductoModel productoModel);
}
