package com.unla.grupo1.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo1.entities.Inventario;
import com.unla.grupo1.entities.Producto;

@Repository("inventarioRepository")
public interface IInventarioRepository extends JpaRepository<Inventario, Serializable> {

	public abstract Inventario findById(int id);
}
