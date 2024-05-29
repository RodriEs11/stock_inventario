package com.unla.grupo1.services;

import java.util.List;

import com.unla.grupo1.entities.UserRole;
import com.unla.grupo1.models.UserRoleModel;

public interface IUserRoleService {
	
	public List<UserRole> getAll();
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel);
	public UserRole getByRole(String role);
}
