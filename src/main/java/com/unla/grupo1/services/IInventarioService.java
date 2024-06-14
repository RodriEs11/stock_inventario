package com.unla.grupo1.services;

import java.util.List;

import com.unla.grupo1.entities.Inventario;
import com.unla.grupo1.models.InventarioModel;


public interface IInventarioService {
	
	public List<Inventario> getAll();
	public InventarioModel getById(int id);
	public InventarioModel insertOrUpdate(InventarioModel inventarioModel);
	public void deleteById(int id);
	
}
