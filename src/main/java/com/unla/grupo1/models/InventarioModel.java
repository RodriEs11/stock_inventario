package com.unla.grupo1.models;

import java.time.LocalDate;

import com.unla.grupo1.entities.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioModel {

	private int id;
	private Producto producto;
	private int cantidadDisponible;
	private LocalDate fechaIngreso;
}
