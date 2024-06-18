package com.unla.grupo1.controllers;

import org.modelmapper.ModelMapper;
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
	
	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping("")
	public ModelAndView proveedores() {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.PROVEEDORES);
		vista.addObject("proveedores", proveedorService.getAll());

		return vista;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView verEditarProveedor(@PathVariable int id) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.EDITAR_PROVEEDOR);
		ProveedorDTO proveedorDTO = modelMapper.map(proveedorService.getById(id).get(), ProveedorDTO.class);
		vista.addObject("proveedor", proveedorDTO);

		return vista;
	}

	@PostMapping("/editar")
	public String editarProveedor(@ModelAttribute("proveedor") ProveedorDTO proveedorDTO, RedirectAttributes message) {

		try {
			proveedorService.insertOrUpdate(proveedorDTO);
			message.addFlashAttribute("message", "El proveedor se editó exitosamente");
			return "redirect:/proveedores";
		
		} catch (Exception e) {
			message.addFlashAttribute("error", "El proveedor no se pudo editar");
			return "redirect:/proveedores";
		}
	}

	@PostMapping("/eliminar/{id}")
	public String eliminarProveedor(@PathVariable int id, RedirectAttributes message) {

		try {
			proveedorService.removeById(id);
			message.addFlashAttribute("message", "El proveedor se eliminó exitosamente");
			return "redirect:/proveedores";
		
		} catch (Exception e) {
			message.addFlashAttribute("error", "El proveedor no se pudo eliminar, verifique si está asociado a un lote");
			return "redirect:/proveedores";
		}
	}

	@PostMapping("/add")
	public String agregarProveedor(@ModelAttribute("proveedor") ProveedorDTO proveedorDTO, RedirectAttributes message) {

		try {
			proveedorService.insertOrUpdate(proveedorDTO);
			message.addFlashAttribute("message", "El proveedor se agregó exitosamente");
			return "redirect:/proveedores";
		
		} catch (Exception e) {
			message.addFlashAttribute("error", "El proveedor no se pudo eliminar, verifique si está asociado a un lote");
			return "redirect:/proveedores";
		}
	}

}
