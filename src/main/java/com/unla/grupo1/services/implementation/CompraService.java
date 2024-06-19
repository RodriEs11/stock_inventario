package com.unla.grupo1.services.implementation;

import org.springframework.stereotype.Service;

import com.unla.grupo1.entities.Compra;
import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.entities.Stock;
import com.unla.grupo1.repositories.ICompraRepository;
import com.unla.grupo1.repositories.IProductoRepository;
import com.unla.grupo1.repositories.IStockRepository;
import com.unla.grupo1.services.ICompraService;

@Service("compraService")
public class CompraService implements ICompraService {

	private ICompraRepository compraRepository;

	private IProductoRepository productoRepository;

	private IStockRepository stockRepository;

	public CompraService(ICompraRepository compraRepository, IProductoRepository productoRepository,
			IStockRepository stockRepository) {
		this.productoRepository = productoRepository;
		this.compraRepository = compraRepository;
		this.stockRepository = stockRepository;
	}

	@Override
	public void agregarProductoACompra(int productoId, int cantidad) {
		
		Producto producto = productoRepository.findById(productoId);
		
		System.out.println("producto en service: " + producto.getId());

		Stock stock = stockRepository.findByProducto(producto);
		
		System.out.println("stock en service: " + stock);
		if (stock.getCantidadActual() < cantidad) {
			throw new RuntimeException("No hay suficiente stock para el producto");
		}

		Compra compra = new Compra();
		compra.setProducto(producto);
		compra.setCantidad(cantidad);
		compraRepository.save(compra);

		stock.setCantidadActual(stock.getCantidadActual() - cantidad);
		stockRepository.save(stock);

	}

}