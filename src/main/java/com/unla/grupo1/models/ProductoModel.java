package com.unla.grupo1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoModel {

	private int id;
	private String nombre;
	private String descripcion;
	private double costo;
	private double precioVenta;
	private int nivelStockMinimo;
}
