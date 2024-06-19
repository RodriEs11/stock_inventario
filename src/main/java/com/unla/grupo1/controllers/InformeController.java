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

import com.unla.grupo1.dtos.InformeDTO;
import com.unla.grupo1.dtos.ProductoDTO;
import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.models.ProductoModel;
import com.unla.grupo1.services.ICompraService;
import com.unla.grupo1.services.IInformeService;
import com.unla.grupo1.services.IProductoService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/informes")
public class InformeController {

	@Autowired
	@Qualifier("informeService")
	private IInformeService informeService;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("compraService")
	private ICompraService compraService;
	
	@GetMapping("")
    public ModelAndView informes() {

        ModelAndView vista = new ModelAndView(ViewRouteHelper.INFORMES);
        vista.addObject("informes", informeService.getAll());
        vista.addObject("productos", productoService.getAll()); // Añadir lista de productos al modelo
       
        return vista;
    }
	
	@PostMapping("/eliminar/{id}")
	public String eliminarInforme(@PathVariable int id) {

		try {
			informeService.removeById(id);
			return "redirect:/informes";
		} catch (Exception e) {
			return "redirect:/informes?message=errordelete";    
		}
	
	}
	
	
	@PostMapping("/generar")
	public String generarInforme(@ModelAttribute("informe") InformeDTO informeDTO) {
	
		informeService.insertOrUpdate(informeDTO);

		return "redirect:/informes";
	}
	
	
	
}
