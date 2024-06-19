package com.unla.grupo1.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo1.dtos.InformeDTO;
import com.unla.grupo1.entities.Informe;
public interface IInformeService {
	
	public List<InformeDTO> getAll();
	public Optional<Informe> getById(int id);
	public InformeDTO insertOrUpdate(InformeDTO informeDTO);
	public void removeById(int id);
	public void calcularCantidadVendidaEnElUltimoMes(Informe informe);
	public void calcularTiempoAlmacenamientoPromedio(Informe informe);
	public void stockTotal(Informe informe);
}
