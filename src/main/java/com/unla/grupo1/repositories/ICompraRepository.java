package com.unla.grupo1.repositories;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo1.entities.Compra;

@Repository("compraRepository")
public interface ICompraRepository extends JpaRepository<Compra, Serializable>{

	@Query("SELECT SUM(o.cantidad) FROM Compra o " +
	           "WHERE o.producto.id = :productoId " +
	           "AND o.createdAt >= :fechaInicio " +
	           "AND o.createdAt <= :fechaFin")
	 public Integer sumCantidadCompradaByProductoAndFecha(@Param("productoId") int productoId,
	                                                  @Param("fechaInicio") LocalDate fechaInicio,
	                                                  @Param("fechaFin") LocalDate fechaFin);
	 
	
}
