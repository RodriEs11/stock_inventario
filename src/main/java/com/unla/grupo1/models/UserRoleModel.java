package com.unla.grupo1.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleModel {

	private int id;
	private String role;

	public UserRoleModel(String role) {
		super();
		this.role = role;
	}

}
