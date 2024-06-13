package com.unla.grupo1.services;

import java.util.List;

import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.models.ProductoModel;


public interface IProductoService {
	
	public List<Producto> getAll();
	public List<Producto> getAllByOrderByNombreAsc();
	public ProductoModel getById(int id);
	public ProductoModel insertOrUpdate(ProductoModel productoModel);
	public void deleteById(int id);
	
}
