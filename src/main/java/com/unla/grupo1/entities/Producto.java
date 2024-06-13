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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	
	@Column(name = "costo")
	private double costo;
	
	@Column(name = "precioVenta")
	private double precioVenta;
	
	@Column(name = "nivelStockMinimo")
	private int nivelStockMinimo;

	@Column(name = "createat")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updateat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(name = "activo")
	private boolean activo;
	
	@OneToOne
	private Proveedor proveedor;
		
}
