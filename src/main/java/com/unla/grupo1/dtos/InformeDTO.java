package com.unla.grupo1.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.unla.grupo1.entities.Compra;
import com.unla.grupo1.entities.Lote;
import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.entities.Stock;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class InformeDTO {
	
	private int id;
	private Producto producto;	
	private Set<Lote> lotes = new HashSet<>();
	private Set<Stock> stocks = new HashSet<>();
	private Set<Compra> compras = new HashSet<>();
	private LocalDate fechaCreacion;
    private int ventasUltimoMes;
    private double tiempoAlmacenamiento;
    private int stockTotal;
}
