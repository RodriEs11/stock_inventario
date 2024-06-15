package com.unla.grupo1.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "lote")
public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	private Producto producto;

	@OneToOne
	private Proveedor proveedor;

	@OneToOne
	private Almacen almacen;

	private int cantidadInicial;

	private int cantidadExistente;

	private double precioDeCompra;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public Lote(Producto producto, int cantidadInicial, int cantidadExistente, Proveedor proveedor) {
		super();
		this.producto = producto;
		this.cantidadInicial = cantidadInicial;
		this.cantidadExistente = cantidadExistente;
		this.proveedor = proveedor;
	}

}