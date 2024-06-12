package com.unla.grupo1.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.models.ProductoModel;

@Component("productoConverter")
public class ProductoConverter {
	
	private final ModelMapper modelMapper;

	public ProductoConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public ProductoModel entityToModel(Producto producto) {
		return modelMapper.map(producto, ProductoModel.class);
	}
	
	public Producto modelToEntity(ProductoModel productoModel) {

		return modelMapper.map(productoModel, Producto.class);
	}

}
