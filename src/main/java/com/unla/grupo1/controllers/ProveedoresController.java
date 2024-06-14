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
import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.models.ProductoModel;
import com.unla.grupo1.models.ProveedorModel;
import com.unla.grupo1.services.IProductoService;
import com.unla.grupo1.services.IProveedorService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/proveedores")
public class ProveedoresController {

	@Autowired
	@Qualifier("proveedorService")
	private IProveedorService proveedorService;

	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

	@GetMapping("")
	public ModelAndView proveedores(Model model, @RequestParam(name = "error", required = false) String error) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.PROVEEDORES);
		
		vista.addObject("proveedores", proveedorService.getAll());
		vista.addObject("proveedor", new ProveedorModel());

		vista.addObject("productos", productoService.getAllByOrderByNombreAsc());
		vista.addObject("producto", new ProductoModel());
		
		model.addAttribute("error", error);

		return vista;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editarProveedor(@PathVariable int id){
		
		ModelAndView vista = new ModelAndView(ViewRouteHelper.EDITAR_PROVEEDOR);
		vista.addObject("proveedor", proveedorService.getById(id));
				
		return vista;
	}
	
	@PostMapping("/editar")
	public String editarProveedor(@ModelAttribute("proveedor") ProveedorModel proveedorModel) {
		
		proveedorService.insertOrUpdate(proveedorModel);
		
		return "redirect:/proveedores";
	}
	
	@PostMapping("/eliminar/{id}")
	public String eliminarProveedor(@PathVariable int id) {

		proveedorService.deleteById(id);

		return "redirect:/proveedores";
	}


	@PostMapping("")
	public String agregarProveedor(@ModelAttribute("proveedor") ProveedorModel proveedorModel) {

		try {
			proveedorService.insertOrUpdate(proveedorModel);
		} catch (Exception e) {
			return "redirect:proveedores?error=producto";
		}

		return "redirect:/proveedores";
	}

}
