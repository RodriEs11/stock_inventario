package com.unla.grupo1.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoDTO {

	private int id;

	private String nombre;

	private String codigo;

	private String descripcion;

	private double costo;

	private double precioVenta;

}
