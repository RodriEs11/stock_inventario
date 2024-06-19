package com.unla.grupo1.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo1.dtos.LoteDTO;
import com.unla.grupo1.entities.Lote;
import com.unla.grupo1.repositories.ILoteRepository;
import com.unla.grupo1.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService {

	private ModelMapper modelMapper;

	private ILoteRepository loteRepository;

	public LoteService(ILoteRepository loteRepository) {
		this.loteRepository = loteRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public Optional<Lote> getById(int id) {
		return loteRepository.findById(id);
	}

	@Override
	public LoteDTO insertOrUpdate(LoteDTO loteDTO) {
		Lote lote = loteRepository.save(modelMapper.map(loteDTO, Lote.class));
		return modelMapper.map(lote, LoteDTO.class);
	}

	@Override
	public List<Lote> getAllByStockId(int id) {

		return loteRepository.findAllByStockId(id);
	}

	@Override
	public List<Lote> findByStock_Producto_Id(int productoId){
		return loteRepository.findByStock_Producto_Id(productoId);
	}
	
	@Override
	public List<LoteDTO> getAll() {
		return loteRepository.findAll().stream().map(lote -> modelMapper.map(lote, LoteDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void removeById(int id) {
		loteRepository.deleteById(id);
	}
}
