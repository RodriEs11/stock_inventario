package com.unla.grupo1.services;

import java.util.List;

import com.unla.grupo1.entities.User;
import com.unla.grupo1.models.UserModel;

public interface IUserService {
	
	public List<User> getAll();
	public UserModel getUserByUsername(String username);
	public UserModel insertOrUpdate(UserModel usuarioModel);
	public boolean remove(int id);
	
	
}
