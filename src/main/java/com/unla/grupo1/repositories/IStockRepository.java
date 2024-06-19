package com.unla.grupo1.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.entities.Stock;

@Repository("stockRepository")
public interface IStockRepository extends JpaRepository<Stock, Serializable>{

	public abstract Optional<Stock> findById(int id);
	
	 @Query("SELECT s FROM Stock s WHERE s.cantidadActual < s.cantidadMinima")
	 List<Stock> findStocksWithLowQuantity();
	 
	 public abstract Stock findByProducto(Producto producto);

	 @Query("SELECT s FROM Stock s INNER JOIN FETCH s.producto p WHERE p.id = :productoId")
	 List<Stock> findAllByProducto(@Param("productoId") int productoId);
	 
}
