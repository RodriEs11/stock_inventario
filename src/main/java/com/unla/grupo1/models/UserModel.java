package com.unla.grupo1.models;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	private int id;
	private String nombre;
	private boolean enabled;
	private String username;
	private String password;
	private Set<UserRoleModel> roles = new HashSet<UserRoleModel>();
	

}
