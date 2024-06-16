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

import com.unla.grupo1.dtos.ProveedorDTO;
import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.services.IProveedorService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/proveedores")
public class ProveedorController {

	@Autowired
	@Qualifier("proveedorService")
	private IProveedorService proveedorService;
	
	@GetMapping("")
	public ModelAndView productos() {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.PROVEEDORES);
		vista.addObject("proveedores", proveedorService.getAll());
		vista.addObject("proveedor", new ProveedorDTO());

		return vista;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editarProveedor(@PathVariable int id) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.EDITAR_PROVEEDOR);
		vista.addObject("producto", proveedorService.getById(id));

		return vista;
	}

	@PostMapping("/add")
	public String agregarProveedor(@ModelAttribute("proveedor") ProveedorDTO proveedorDTO) {

		proveedorService.insertOrUpdate(proveedorDTO);

		return "redirect:/proveedores";
	}
	
	
}
