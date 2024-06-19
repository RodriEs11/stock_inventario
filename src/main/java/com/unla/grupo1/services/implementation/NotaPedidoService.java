package com.unla.grupo1.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo1.dtos.NotaPedidoDTO;
import com.unla.grupo1.entities.NotaPedido;
import com.unla.grupo1.repositories.INotaPedidoRepository;
import com.unla.grupo1.services.INotaPedidoService;

@Service("notaPedidoService")
public class NotaPedidoService implements INotaPedidoService {
	
	private ModelMapper modelMapper;

	private INotaPedidoRepository notaPedidoRepository;

	public NotaPedidoService(INotaPedidoRepository notaPedidoRepository) {
		this.notaPedidoRepository = notaPedidoRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public Optional<NotaPedido> getById(int id) {

		return notaPedidoRepository.findById(id);
	}
	
	@Override
	public List<NotaPedidoDTO> getAll() {
		return notaPedidoRepository.findAll().stream().map(notaPedido -> modelMapper.map(notaPedido, NotaPedidoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public NotaPedidoDTO insertOrUpdate(NotaPedidoDTO notaPedidoDTO) {
		NotaPedido notaPedido = notaPedidoRepository.save(modelMapper.map(notaPedidoDTO, NotaPedido.class));
		return modelMapper.map(notaPedido, NotaPedidoDTO.class);
	}

	@Override
	public void removeById(int id) {
		notaPedidoRepository.deleteById(id);
	}
}
