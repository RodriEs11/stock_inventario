package com.unla.grupo1.entities;


import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_role", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "user_id" }))
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany(mappedBy = "userRoles")
	@JsonIgnore
	private Set<User> users;

	@Column(name = "role", nullable = false, length = 100)
	private String role;

	@Column(name = "createat")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updateat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public UserRole(int id, Set<User> users, String role) {

		this.id = id;
		this.users = users;
		this.role = role;
	}
	
	
}
