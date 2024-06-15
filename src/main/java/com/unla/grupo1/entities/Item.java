package com.unla.grupo1.entities;

import java.time.LocalDateTime;


import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	int cantidad;
	
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "idCompra")
	private Compra compra;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
}
