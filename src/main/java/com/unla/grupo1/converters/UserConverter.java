package com.unla.grupo1.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.unla.grupo1.entities.User;
import com.unla.grupo1.models.UserModel;



@Component("userConverter")
public class UserConverter {

	private final ModelMapper modelMapper;

	
	public UserConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public UserModel entityToModel(User user) {

		return modelMapper.map(user, UserModel.class);
	}

	public User modelToEntity(UserModel userModel) {

		return modelMapper.map(userModel, User.class);
	}

}
