package com.unla.grupo1.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.entities.Proveedor;
import com.unla.grupo1.models.ProductoModel;
import com.unla.grupo1.models.ProveedorModel;

@Component("proveedorConverter")
public class ProveedorConverter {

	private final ModelMapper modelMapper;

	public ProveedorConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ProveedorModel entityToModel(Proveedor proveedor) {
		return modelMapper.map(proveedor, ProveedorModel.class);
	}

	public Proveedor modelToEntity(ProveedorModel proveedorModel) {

		return modelMapper.map(proveedorModel, Proveedor.class);
	}

}
