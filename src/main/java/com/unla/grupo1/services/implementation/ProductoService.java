package com.unla.grupo1.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo1.dtos.ProductoDTO;
import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.repositories.IProductoRepository;
import com.unla.grupo1.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService {

	private ModelMapper modelMapper;

	private IProductoRepository productoRepository;

	public ProductoService(IProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public List<Producto> getAll() {

		return productoRepository.findAll();
	}

	@Override
	public ProductoDTO getById(int id) {

		return modelMapper.map(productoRepository.findById(id), ProductoDTO.class);
	}

	@Override
	public ProductoDTO insertOrUpdate(ProductoDTO productoDTO) {
		Producto producto = productoRepository.save(modelMapper.map(productoDTO, Producto.class));
		return modelMapper.map(producto, ProductoDTO.class);
	}

	@Override
	public void deleteById(int id) {
		Producto producto = productoRepository.findById(id);
		productoRepository.delete(producto);
	}

}