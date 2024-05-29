package com.unla.grupo1.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo1.converters.UserRoleConverter;
import com.unla.grupo1.entities.UserRole;
import com.unla.grupo1.models.UserRoleModel;
import com.unla.grupo1.repositories.IUserRoleRepository;
import com.unla.grupo1.services.IUserRoleService;


@Service("userRoleService")
public class UserRoleService implements IUserRoleService {

	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
	@Autowired
	@Qualifier("userRoleConverter")
	private UserRoleConverter userRoleConverter;

	@Override
	public List<UserRole> getAll() {
		
		return userRoleRepository.findAll();

	}

	@Override
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel) {
		UserRole userRole = userRoleRepository.save(userRoleConverter.modelToEntity(userRoleModel));
		return userRoleConverter.entityToModel(userRole);
	}

	@Override
	public UserRole getByRole(String role) {

		return userRoleRepository.findByRole(role);
	}

}
