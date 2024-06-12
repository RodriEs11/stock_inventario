package com.unla.grupo1.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo1.converters.ProductoConverter;
import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.models.ProductoModel;
import com.unla.grupo1.repositories.IProductoRepository;
import com.unla.grupo1.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService{

	
	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;

	@Override
	public List<Producto> getAll() {
		return productoRepository.findAll();
	}

	@Override
	public ProductoModel insertOrUpdate(ProductoModel productoModel) {
		Producto producto = productoRepository.save(productoConverter.modelToEntity(productoModel));
		return productoConverter.entityToModel(producto);
	}
	
}
