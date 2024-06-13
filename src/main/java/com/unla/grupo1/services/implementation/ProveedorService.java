package com.unla.grupo1.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo1.converters.ProductoConverter;
import com.unla.grupo1.converters.ProveedorConverter;
import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.entities.Proveedor;
import com.unla.grupo1.models.ProductoModel;
import com.unla.grupo1.models.ProveedorModel;
import com.unla.grupo1.repositories.IProductoRepository;
import com.unla.grupo1.repositories.IProveedorRepository;
import com.unla.grupo1.services.IProductoService;
import com.unla.grupo1.services.IProveedorService;

@Service("proveedorService")
public class ProveedorService implements IProveedorService{

	
	@Autowired
	@Qualifier("proveedorRepository")
	private IProveedorRepository proveedorRepository;
	
	@Autowired
	@Qualifier("proveedorConverter")
	private ProveedorConverter proveedorConverter;

	@Override
	public List<Proveedor> getAll() {
		return proveedorRepository.findAll();
	}

	@Override
	public ProveedorModel insertOrUpdate(ProveedorModel proveedorModel) {
		Proveedor proveedor = proveedorRepository.save(proveedorConverter.modelToEntity(proveedorModel));
		return proveedorConverter.entityToModel(proveedor);
	}

	@Override
	public ProveedorModel getById(int id) {
		
		return proveedorConverter.entityToModel(proveedorRepository.findById(id));
	}

	@Override
	public void deleteById(int id) {
		
		Proveedor proveedor = proveedorRepository.findById(id);
		proveedorRepository.delete(proveedor);

	}

	@Override
	public ProveedorModel getByProducto(Producto producto) {
		return proveedorConverter.entityToModel(proveedorRepository.findByProducto(producto));
	}
	
}
