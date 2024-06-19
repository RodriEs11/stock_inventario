package com.unla.grupo1.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.unla.grupo1.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable>{

	public abstract Optional<Lote> findById(int id);
	public abstract List<Lote> findAllByStockId(int id);
	//-------------------
	@Query("SELECT l FROM Lote l INNER JOIN FETCH l.stock s INNER JOIN FETCH s.producto p WHERE p.id = (:productoId)")
	public abstract List<Lote> findByStock_Producto_Id(@Param("productoId") int productoId);
}
