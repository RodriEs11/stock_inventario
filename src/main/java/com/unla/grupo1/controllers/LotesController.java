package com.unla.grupo1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo1.dtos.LoteDTO;
import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.services.ILoteService;
import com.unla.grupo1.services.IProductoService;
import com.unla.grupo1.services.IProveedorService;
import com.unla.grupo1.services.IStockService;


@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("stock/lotes")
public class LotesController {

	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	
	@Autowired
	@Qualifier("proveedorService")
	private IProveedorService proveedorService;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("stockService")
	private IStockService stockService;
	
	@GetMapping("/{id}")
	public ModelAndView lotes(@PathVariable("id") int id) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.LOTES);
		
		vista.addObject("lotes", loteService.getAllByStockId(id));
		vista.addObject("lote", loteService.getAllByStockId(id));
		
		vista.addObject("id", id);
		
		vista.addObject("proveedores", proveedorService.getAll());
		vista.addObject("productos", productoService.getAll());		
		
		return vista;
	}


	@PostMapping("/add")
	public String agregarLote(@ModelAttribute("lote") LoteDTO loteDTO) {
	
		stockService.sumarLote(loteDTO.getStock(), loteDTO.getCantidadRecibida());
		
		loteService.insertOrUpdate(loteDTO);
		
		int id = loteDTO.getStock().getId();

		return "redirect:/stock/lotes/" + id;
	}
}
