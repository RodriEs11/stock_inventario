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

import com.unla.grupo1.dtos.StockDTO;
import com.unla.grupo1.helpers.ViewRouteHelper;
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

	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping("")
	public ModelAndView stocks() {
		
		ModelAndView vista = new ModelAndView(ViewRouteHelper.STOCK);
		
		vista.addObject("stocks", stockService.getAll());
		vista.addObject("productos", productoService.getAll());
	
		return vista;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView verEditarStock(@PathVariable int id) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.EDITAR_STOCK);
		StockDTO stockDTO = modelMapper.map(stockService.getById(id).get(), StockDTO.class);
		vista.addObject("stock", stockDTO);

		return vista;
	}

	@PostMapping("/editar")
	public String editarStock(@ModelAttribute("stock") StockDTO stockDTO,  RedirectAttributes message) {

		try {	
			stockService.insertOrUpdate(stockDTO);
			message.addFlashAttribute("message", "El stock se editó exitosamente");
			return "redirect:/stock";
			
		}catch (Exception e) {
			message.addFlashAttribute("error", "No se pudo editar el stock");
			return "redirect:/stock";
		}
	}

	@PostMapping("/eliminar/{id}")
	public String eliminarStock(@PathVariable int id, RedirectAttributes message) {

		try {
			stockService.removeById(id);
			message.addFlashAttribute("message", "El stock se eliminó exitosamente");
			return "redirect:/stock";
			
		} catch (Exception e) {
			message.addFlashAttribute("error", "No se pudo eliminar el stock, verifique si tiene lotes asociados");
			return "redirect:/stock";
		}
	}

	@PostMapping("/add")
	public String agregarStock(@ModelAttribute("stock") StockDTO stockDTO, RedirectAttributes message) {

		try {
			stockService.insertOrUpdate(stockDTO);
			message.addFlashAttribute("message", "El stock se agregó exitosamente");
			return "redirect:/stock";
		
		} catch (Exception e) {
			message.addFlashAttribute("error", "Error, verifique si ya existe un stock del mismo producto");
			return "redirect:/stock";
		}
	}

}
