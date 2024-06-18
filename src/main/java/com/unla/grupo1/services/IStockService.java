package com.unla.grupo1.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo1.dtos.StockDTO;
import com.unla.grupo1.entities.Stock;

public interface IStockService {

	public List<StockDTO> getAll();
	public Optional<Stock> getById(int id);
	public StockDTO insertOrUpdate(StockDTO stockDTO);
	public void removeById(int id);
	public void sumarLote(Stock stock, int cantidad);
	public void restarLote(Stock stock, int cantidad);
	public String checkCantidadesStock();

}
