package com.unla.grupo1.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo1.dtos.StockDTO;
import com.unla.grupo1.entities.Stock;
import com.unla.grupo1.repositories.IStockRepository;
import com.unla.grupo1.services.IStockService;

@Service("stockService")
public class StockService implements IStockService {

	private ModelMapper modelMapper;

	private IStockRepository stockRepository;

	public StockService(IStockRepository stockRepository) {
		this.stockRepository = stockRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public List<StockDTO> getAll() {
		return stockRepository.findAll().stream().map(stock -> modelMapper.map(stock, StockDTO.class))
				.collect(Collectors.toList());
	}

	public Optional<StockDTO> getById(int id) {

		return stockRepository.findById(id);
	}

	public StockDTO insertOrUpdate(StockDTO stockDTO) {

		Stock stock = stockRepository.save(modelMapper.map(stockDTO, Stock.class));
		return modelMapper.map(stock, StockDTO.class);
	}
	
	public void sumarLote(Stock stock, int cantidad) {
		
		StockDTO stockDTO = modelMapper.map(stock, StockDTO.class);
		
		int cantActual = stockDTO.getCantidadActual();
		
		stockDTO.setCantidadActual(cantActual + cantidad);
		
		insertOrUpdate(stockDTO);
	};
}
