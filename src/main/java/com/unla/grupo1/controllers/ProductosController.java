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
import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.models.ProductoModel;
import com.unla.grupo1.services.IProductoService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/productos")
public class ProductosController {

	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	

	@GetMapping("")
	public ModelAndView productos(){
		
		ModelAndView vista = new ModelAndView(ViewRouteHelper.PRODUCTOS);
		vista.addObject("productos",productoService.getAll());
		vista.addObject("producto",new ProductoModel());
		
		return vista;
	}
		
	@GetMapping("/editar/{id}")
	public ModelAndView editarProducto(@PathVariable int id){
		
		ModelAndView vista = new ModelAndView(ViewRouteHelper.EDITAR_PRODUCTOS);
		vista.addObject("producto", productoService.getById(id));
				
		return vista;
	}
		
	@PostMapping("/editar")
	public String editarProducto(@ModelAttribute("producto") ProductoModel productoModel) {
		
		productoService.insertOrUpdate(productoModel);
		
		return "redirect:/productos";
	}
	
	@PostMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable int id) {
		
		productoService.deleteById(id);
		
		return "redirect:/productos";
	}
	

	@PostMapping("")
	public String agregarProductos(@ModelAttribute("producto") ProductoModel productoModel) {
		productoModel.setActivo(true); //Activo por default
		productoService.insertOrUpdate(productoModel);
		
		return "redirect:/productos";
	}
	
}
