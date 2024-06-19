package com.unla.grupo1.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo1.entities.NotaPedido;

@Repository("notaPedidoRepository")
public interface INotaPedidoRepository extends JpaRepository<NotaPedido, Serializable>{

	public abstract Optional<NotaPedido> findById(int id);
}
