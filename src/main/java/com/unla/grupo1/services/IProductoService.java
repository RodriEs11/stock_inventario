package com.unla.grupo1.services;

import java.util.List;

import com.unla.grupo1.dtos.ProductoDTO;
import com.unla.grupo1.entities.Producto;

public interface IProductoService {
	
	public List<Producto> getAll();
	public ProductoDTO getById(int id);
	public ProductoDTO insertOrUpdate(ProductoDTO productoDTO);
	public void deleteById(int id);

}
