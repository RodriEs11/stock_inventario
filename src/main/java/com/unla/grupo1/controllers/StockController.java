package com.unla.grupo1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo1.dtos.StockDTO;
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
		vista.addObject("productos", productoService.getAll());

		vista.addObject("stocks", stockService.getAll());

		return vista;
	}

	@PostMapping("/add")
	public String agregarProveedor(@ModelAttribute("stock") StockDTO stockDTO) {

		stockService.insertOrUpdate(stockDTO);

		return "redirect:/stock";
	}

}
