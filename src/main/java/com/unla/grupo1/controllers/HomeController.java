package com.unla.grupo1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo1.entities.Inventario;
import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.services.IInventarioService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	@Qualifier("inventarioService")
	private IInventarioService inventarioService;

	@GetMapping("")
	public String index() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public ModelAndView home() {

		List<Inventario> inventarios = inventarioService.getAll();

		ModelAndView vista = new ModelAndView(ViewRouteHelper.INDEX);

		int cantAlertasStockMinimo = 0;
		
		for (Inventario inventario : inventarios) {
			if (inventario.getCantidadDisponible() < inventario.getProducto().getNivelStockMinimo()) {
				cantAlertasStockMinimo++;
			}
		}

		vista.addObject("inventarios", inventarios);
		vista.addObject("cantAlertasStockMinimo", cantAlertasStockMinimo);
		return vista;
	}

}
