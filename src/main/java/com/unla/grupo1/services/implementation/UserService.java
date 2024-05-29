package com.unla.grupo1.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo1.converters.UserConverter;
import com.unla.grupo1.entities.User;
import com.unla.grupo1.entities.UserRole;
import com.unla.grupo1.models.UserModel;
import com.unla.grupo1.repositories.IUserRepository;
import com.unla.grupo1.services.IUserService;

@Service("userService")
public class UserService implements UserDetailsService, IUserService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);

		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
	}

	private org.springframework.security.core.userdetails.User buildUser(User user,
			List<GrantedAuthority> grantedAuthorities) {

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, grantedAuthorities);

	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {

		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

		for (UserRole userRole : userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}

	@Override
	public List<User> getAll() {

		return userRepository.findAll();
	}

	@Override
	public UserModel insertOrUpdate(UserModel usuarioModel) {
		User user = userRepository.save(userConverter.modelToEntity(usuarioModel));
		return userConverter.entityToModel(user);
	}

	@Override
	public boolean remove(int id) {

		try {
			userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public User findUserById(int userId) {

		return userRepository.findById(userId);
	}

	@Override
	public UserModel getUserByUsername(String username) {
		return userConverter.entityToModel(userRepository.findByUsername(username));
	}

}
