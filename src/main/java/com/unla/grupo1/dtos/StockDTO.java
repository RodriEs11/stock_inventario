package com.unla.grupo1.dtos;

import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.entities.Stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockDTO {

	int id;
	private Producto producto;
	private Stock stock;
	int cantidadMinima;
	int cantidadActual;
	int cantidadDeseable;	
}
