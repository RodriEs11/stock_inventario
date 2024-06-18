package com.unla.grupo1.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo1.dtos.ProveedorDTO;
import com.unla.grupo1.entities.Proveedor;
import com.unla.grupo1.repositories.IProveedorRepository;
import com.unla.grupo1.services.IProveedorService;

@Service("proveedorService")
public class ProveedorService implements IProveedorService {

	private ModelMapper modelMapper;

	private IProveedorRepository proveedorRepository;

	public ProveedorService(IProveedorRepository proveedorRepository) {
		this.proveedorRepository = proveedorRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public Optional<Proveedor> getById(int id) {
		return proveedorRepository.findById(id);
	}

	@Override
	public ProveedorDTO insertOrUpdate(ProveedorDTO proveedorDTO) {
		Proveedor proveedor = proveedorRepository.save(modelMapper.map(proveedorDTO, Proveedor.class));
		return modelMapper.map(proveedor, ProveedorDTO.class);
	}

	@Override
	public List<ProveedorDTO> getAll() {
		return proveedorRepository.findAll().stream().map(proveedor -> modelMapper.map(proveedor, ProveedorDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void removeById(int id) {

		proveedorRepository.deleteById(id);
	}
}
