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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.unla.grupo1.dtos.LoteDTO;
import com.unla.grupo1.entities.Lote;
import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.services.ILoteService;
import com.unla.grupo1.services.IProductoService;
import com.unla.grupo1.services.IProveedorService;
import com.unla.grupo1.services.IStockService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/lotes")
public class LotesController {

	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;

	@Autowired
	@Qualifier("proveedorService")
	private IProveedorService proveedorService;

	@Autowired
	@Qualifier("stockService")
	private IStockService stockService;

	@GetMapping("/stock{id}")
	public ModelAndView lotes(@PathVariable("id") int id) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.LOTES);

		vista.addObject("lotes", loteService.getAllByStockId(id));
		vista.addObject("proveedores", proveedorService.getAll());
		vista.addObject("id", id);

		return vista;
	}

	@PostMapping("/eliminar/{id}")
	public String eliminarLote(@PathVariable int id, RedirectAttributes message) {
		
		Lote lote = loteService.getById(id).get();
		int idStock = lote.getStock().getId();
	
		try {
			loteService.removeById(id);
			message.addFlashAttribute("message", "El lote se eliminó exitosamente");
			return "redirect:/lotes/stock" + idStock;
			
		} catch (Exception e) {
			message.addFlashAttribute("error", "No se pudo eliminar el lote");
			return "redirect:/lotes/stock" + idStock;
		}
	}

	@PostMapping("/add")
	public String agregarLote(@ModelAttribute("lote") LoteDTO loteDTO, RedirectAttributes message) {

		int idStock = loteDTO.getStock().getId();
		
		try {
			stockService.sumarLote(loteDTO.getStock(), loteDTO.getCantidadRecibida());
			loteService.insertOrUpdate(loteDTO);
			message.addFlashAttribute("message", "El lote se agregó exitosamente");
			return "redirect:/lotes/stock" + idStock;
			
		} catch (Exception e) {
			message.addFlashAttribute("error", "No se pudo agregar el lote");
			return "redirect:/lotes/stock" + idStock;
		}
	}
}
