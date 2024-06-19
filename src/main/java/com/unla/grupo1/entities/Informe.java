package com.unla.grupo1.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "informe")
public class Informe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private Producto producto;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "informe")
    private Set<Lote> lotes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "informe")
    private Set<Stock> stocks = new HashSet<>();
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "informe")
    private Set<Compra> compras = new HashSet<>();
    
    @CreationTimestamp
    private LocalDate fechaCreacion;
    private int ventasUltimoMes;
    private double tiempoAlmacenamiento;
    private int stockTotal;
    
    
}
