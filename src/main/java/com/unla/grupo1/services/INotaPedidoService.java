package com.unla.grupo1.services;
import java.util.List;
import java.util.Optional;
import com.unla.grupo1.dtos.NotaPedidoDTO;
import com.unla.grupo1.entities.NotaPedido;

public interface INotaPedidoService {

	public Optional<NotaPedido> getById(int id);
	public List<NotaPedidoDTO> getAll();
	public NotaPedidoDTO insertOrUpdate(NotaPedidoDTO notaPedidoDTO);
	public void removeById(int id);
}
