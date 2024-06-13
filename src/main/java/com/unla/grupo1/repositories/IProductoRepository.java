package com.unla.grupo1.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo1.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

	public abstract Producto findById(int id);
	public abstract boolean removeById(int id);
	public abstract List<Producto> findAllByOrderByNombreAsc();
}
