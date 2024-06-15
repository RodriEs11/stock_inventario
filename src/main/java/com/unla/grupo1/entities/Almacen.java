package com.unla.grupo1.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Almacen extends Stock {

	int cantDeseable;

	int cantMinima;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "almacen")
	private Set<Lote> lotes = new HashSet<>();

}
