package com.unla.grupo1.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


import com.unla.grupo1.entities.UserRole;
import com.unla.grupo1.models.UserRoleModel;

@Component("userRoleConverter")
public class UserRoleConverter {

	private final ModelMapper modelMapper;

	public UserRoleConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public UserRoleModel entityToModel(UserRole userRole) {

		return modelMapper.map(userRole, UserRoleModel.class);
	}

	public UserRole modelToEntity(UserRoleModel userRoleModel) {

		return modelMapper.map(userRoleModel, UserRole.class);
	}
}
