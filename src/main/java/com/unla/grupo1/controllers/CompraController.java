package com.unla.grupo1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.services.ICompraService;
import com.unla.grupo1.services.IProductoService;

@Controller
@PreAuthorize("hasRole('ROLE_CLIENTE')")
@RequestMapping("/Compra")
public class CompraController {
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("compraService")
	private ICompraService compraService;
	
	@GetMapping("")
	public ModelAndView productos() {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.COMPRA);
		vista.addObject("productos", productoService.getAll());

		return vista;
	}
	
    @PostMapping("/agregarProducto")
    public String agregarCompraProducto(@RequestParam int productoId, @RequestParam int cantidad, RedirectAttributes message) {
    	System.out.println("Producto id: " + productoId);
    	System.out.println("Cantidad: " + cantidad);
    	try {
    		compraService.agregarProductoACompra(productoId, cantidad);
			message.addFlashAttribute("message", "Compra realizada exitosamente y stock disponible actualizado.");
			return "redirect:/Compra";
		} catch (Exception e) {
			message.addFlashAttribute("error", "Stock insuficiente, no se pudo realizar la compra.");
			return "redirect:/Compra";
		}	
    	
    }
	

}