package com.unla.grupo1.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo1.dtos.StockDTO;
import com.unla.grupo1.entities.Stock;

public interface IStockService {

	public List<StockDTO> getAll();
	public Optional<StockDTO> getById(int id);
	public StockDTO insertOrUpdate(StockDTO stockDTO);
	public void sumarLote(Stock stock, int cantidad);
}
