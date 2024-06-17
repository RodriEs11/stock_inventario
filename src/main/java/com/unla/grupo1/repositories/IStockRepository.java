package com.unla.grupo1.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo1.dtos.StockDTO;
import com.unla.grupo1.entities.Stock;

@Repository("stockRepository")
public interface IStockRepository extends JpaRepository<Stock, Serializable>{

	public abstract Optional<StockDTO> findById(int id);

}
