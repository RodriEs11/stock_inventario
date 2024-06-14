package com.unla.grupo1.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.unla.grupo1.entities.Inventario;
import com.unla.grupo1.models.InventarioModel;

@Component("inventarioConverter")
public class InventarioConverter {
	
	private final ModelMapper modelMapper;

	public InventarioConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public InventarioModel entityToModel(Inventario inventario) {
		return modelMapper.map(inventario, InventarioModel.class);
	}
	
	public Inventario modelToEntity(InventarioModel inventarioModel) {

		return modelMapper.map(inventarioModel, Inventario.class);
	}

}
