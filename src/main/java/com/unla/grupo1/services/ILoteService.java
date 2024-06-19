package com.unla.grupo1.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo1.dtos.LoteDTO;
import com.unla.grupo1.entities.Lote;

public interface ILoteService {
	
	public List<LoteDTO> getAll();
	public Optional<Lote> getById(int id);
	public LoteDTO insertOrUpdate(LoteDTO loteDTO);
	public List<Lote> getAllByStockId(int id);
	public List<Lote> findByStock_Producto_Id(int productoId);
	public void removeById(int id);
}
