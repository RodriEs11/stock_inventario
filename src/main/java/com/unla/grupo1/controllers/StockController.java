package com.unla.grupo1.controllers;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unla.grupo1.dtos.StockDTO;
import com.unla.grupo1.entities.Stock;
import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.services.ILoteService;
import com.unla.grupo1.services.IProductoService;
import com.unla.grupo1.services.IStockService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/stock")
public class StockController {

	@Autowired
	@Qualifier("stockService")
	private IStockService stockService;

	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;

	@GetMapping("")
	public ModelAndView stocks() {
		
		ModelAndView vista = new ModelAndView(ViewRouteHelper.STOCK);
		

		if (stockService.getStocksWithLowQuantity().size() > 0) {
			
			vista.addObject("nivelStock", "Cantidad actual por debajo de la minima en los stocks: " + stockService.getStocksWithLowQuantity().stream()
            .map(Stock::getId)
            .collect(Collectors.toList()));
		}
		
		vista.addObject("productos", productoService.getAll());
		vista.addObject("stocks", stockService.getAll());

		
		return vista;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editarStock(@PathVariable int id) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.EDITAR_STOCK);
		vista.addObject("stock", stockService.getById(id).get());

		return vista;
	}

	@PostMapping("/editar")
	public String editarStock(@ModelAttribute("stock") StockDTO stockDTO,  RedirectAttributes message) {

		stockService.insertOrUpdate(stockDTO);

		message.addFlashAttribute("message", "El stock se edito exitosamente");
		return "redirect:/stock";
	}

	@PostMapping("/eliminar/{id}")
	public String eliminarStock(@PathVariable int id, RedirectAttributes message) {

		try {
			stockService.removeById(id);
			message.addFlashAttribute("message", "El stock se elimino exitosamente");
			return "redirect:/stock";
		} catch (Exception e) {
			message.addFlashAttribute("error", "No se pudo eliminar el stock porque tiene lotes asociados");
			return "redirect:/stock";
		}
	}

	@PostMapping("/add")
	public String agregarStock(@ModelAttribute("stock") StockDTO stockDTO, RedirectAttributes message) {

		try {
			stockService.insertOrUpdate(stockDTO);
			message.addFlashAttribute("message", "El stock se agrego exitosamente");
			return "redirect:/stock";
		} catch (Exception e) {
			message.addFlashAttribute("error", "Error, ya existe un stock del mismo producto");
			return "redirect:/stock";
		}
	}

}
