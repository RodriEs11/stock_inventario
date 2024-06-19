package com.unla.grupo1.services.implementation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo1.dtos.InformeDTO;
import com.unla.grupo1.entities.Compra;
import com.unla.grupo1.entities.Informe;
import com.unla.grupo1.entities.Lote;
import com.unla.grupo1.entities.Stock;
import com.unla.grupo1.repositories.ICompraRepository;
import com.unla.grupo1.repositories.IInformeRepository;
import com.unla.grupo1.repositories.ILoteRepository;
import com.unla.grupo1.repositories.IStockRepository;
import com.unla.grupo1.services.IInformeService;

@Service("informeService")
public class InformeService implements IInformeService{

	private ModelMapper modelMapper;
	
	private IInformeRepository informeRepository;
	
	private ICompraRepository compraRepository;
    
	private ILoteRepository loteRepository;
	
	private IStockRepository stockRepository;
	
    @Autowired
    public InformeService(IInformeRepository informeRepository, ICompraRepository compraRepository, ILoteRepository loteRepository, IStockRepository stockRepository) {
        this.informeRepository = informeRepository;
        this.loteRepository = loteRepository;
        this.compraRepository = compraRepository;
        this.stockRepository = stockRepository;
        this.modelMapper = new ModelMapper();
    }

	@Override
	public List<InformeDTO> getAll(){
		return informeRepository.findAll().stream().map(informe -> modelMapper.map(informe, InformeDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<Informe> getById(int id){
		return informeRepository.findById(id);
	}
	
	@Override
	public InformeDTO insertOrUpdate(InformeDTO informeDTO) {
		
		Informe informe = modelMapper.map(informeDTO, Informe.class);
	    calcularCantidadVendidaEnElUltimoMes(informe);
	    calcularTiempoAlmacenamientoPromedio(informe);
	    stockTotal(informe);
	    informe = informeRepository.save(informe);

	    return modelMapper.map(informe, InformeDTO.class);
	}
	
	@Override
	public void removeById(int id) {
		informeRepository.deleteById(id);
	}
	
	@Override
	public void calcularCantidadVendidaEnElUltimoMes(Informe informe) {
		LocalDateTime fechaInicio = LocalDateTime.now().minusMonths(1); 
        LocalDateTime fechaFin = LocalDateTime.now(); 
        Integer cantidadVendida = 0;
        
        List<Compra> comprasTotales = compraRepository.findComprasBetweenFechasAndByProducto(fechaInicio, fechaFin, informe.getProducto().getId());
        
        for (Compra compra : comprasTotales) {
			cantidadVendida+= compra.getCantidad();
		}
        
		informe.setVentasUltimoMes(cantidadVendida != null ? cantidadVendida : 0);
	}
	
	@Override
	public void calcularTiempoAlmacenamientoPromedio(Informe informe) {
		
		List<Lote> lotes = loteRepository.findByStock_Producto_Id(informe.getProducto().getId());
		
		if (lotes.isEmpty()) {
	        // Manejo de caso donde no hay lotes asociados para evitar division entre 0
	        informe.setTiempoAlmacenamiento(0);
	        return;
	    }
		
		double totalDiasAlmacenamiento = 0;
		
		for (Lote lote : lotes) {
            LocalDateTime fechaRecepcion = lote.getCreatedAt();
            LocalDateTime fechaActual = LocalDateTime.now();
            long diasAlmacenamiento = ChronoUnit.DAYS.between(fechaRecepcion, fechaActual);
            totalDiasAlmacenamiento += diasAlmacenamiento;
        }
		
		informe.setTiempoAlmacenamiento(totalDiasAlmacenamiento / lotes.size());
	}
	
	public void stockTotal(Informe informe) {
		
		int total = 0;
		List<Stock> stocksPorProducto = stockRepository.findAllByProducto(informe.getProducto().getId());
		
		for (Stock stock : stocksPorProducto) {
			total = total + stock.getCantidadActual();
		}
		
		informe.setStockTotal(total);
		
	}
	
	
}
