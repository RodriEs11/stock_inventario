package com.unla.grupo1.models;

import com.unla.grupo1.entities.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorModel {

	private int id;
	private String nombre;
	private String telefono;
	private Producto producto;
}
