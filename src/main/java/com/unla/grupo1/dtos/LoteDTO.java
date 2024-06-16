package com.unla.grupo1.dtos;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.unla.grupo1.entities.Proveedor;
import com.unla.grupo1.entities.Stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteDTO {

	private int id;
	private Proveedor proveedor;
	private Stock stock;
	private int cantidadRecibida;
	private double precioDeCompra;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime createdAt;
	
}