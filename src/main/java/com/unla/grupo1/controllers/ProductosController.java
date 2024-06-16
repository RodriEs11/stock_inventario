package com.unla.grupo1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo1.dtos.ProductoDTO;
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
	public ModelAndView productos(Model model, @RequestParam(name="message", required = false) String message) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.PRODUCTOS);
		vista.addObject("productos", productoService.getAll());
		vista.addObject("producto", new ProductoModel());
		
		model.addAttribute("message", message);

		return vista;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editarProducto(@PathVariable int id) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.EDITAR_PRODUCTOS);
		vista.addObject("producto", productoService.getById(id));

		return vista;
	}

	@PostMapping("/editar")
	public String editarProducto(@ModelAttribute("producto") ProductoDTO productoDTO) {

		productoService.insertOrUpdate(productoDTO);

		return "redirect:/productos?message=success-edit";
	}

	@PostMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable int id) {

		try {
			productoService.deleteById(id);
			return "redirect:/productos";
		} catch (Exception e) {
	            return "redirect:/productos?message=errordelete";
		}
	
	}

	@PostMapping("")
	public String agregarProductos(@ModelAttribute("producto") ProductoDTO productoDTO) {
	
		productoService.insertOrUpdate(productoDTO);

		return "redirect:/productos?message=success-add";
	}

}
