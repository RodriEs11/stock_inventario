package com.unla.grupo1.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo1.converters.InventarioConverter;
import com.unla.grupo1.converters.ProductoConverter;
import com.unla.grupo1.entities.Inventario;
import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.models.InventarioModel;
import com.unla.grupo1.models.ProductoModel;
import com.unla.grupo1.repositories.IInventarioRepository;
import com.unla.grupo1.repositories.IProductoRepository;
import com.unla.grupo1.services.IInventarioService;
import com.unla.grupo1.services.IProductoService;

@Service("inventarioService")
public class InventarioService implements IInventarioService{

	
	@Autowired
	@Qualifier("inventarioRepository")
	private IInventarioRepository inventarioRepository;
	
	@Autowired
	@Qualifier("inventarioConverter")
	private InventarioConverter inventarioConverter;

	@Override
	public List<Inventario> getAll() {
		return inventarioRepository.findAll();
	}

	@Override
	public InventarioModel insertOrUpdate(InventarioModel inventarioModel) {
		Inventario producto = inventarioRepository.save(inventarioConverter.modelToEntity(inventarioModel));
		return inventarioConverter.entityToModel(producto);
	}

	@Override
	public InventarioModel getById(int id) {
		
		return inventarioConverter.entityToModel(inventarioRepository.findById(id));
	}

	@Override
	public void deleteById(int id) {
		
		Inventario inventario = inventarioRepository.findById(id);
		inventarioRepository.delete(inventario);

	}



	
}
