package com.unla.grupo1.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo1.entities.Compra;
import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.entities.Stock;

@Repository("compraRepository")
public interface ICompraRepository extends JpaRepository<Compra, Serializable>{


    @Query("SELECT c FROM Compra c INNER JOIN FETCH c.producto p WHERE c.createdAt BETWEEN :fechaInicio AND :fechaFin AND p.id = :productoId")
    List<Compra> findComprasBetweenFechasAndByProducto(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin, @Param("productoId") int productoId);
	
}
