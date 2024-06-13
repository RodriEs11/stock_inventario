package com.unla.grupo1.services;

import java.util.List;

import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.entities.Proveedor;
import com.unla.grupo1.models.ProductoModel;
import com.unla.grupo1.models.ProveedorModel;


public interface IProveedorService {
	
	public List<Proveedor> getAll();
	public ProveedorModel getById(int id);
	public ProveedorModel getByProducto(Producto producto);
	public ProveedorModel insertOrUpdate(ProveedorModel proveedorModel);
	public void deleteById(int id);
}
