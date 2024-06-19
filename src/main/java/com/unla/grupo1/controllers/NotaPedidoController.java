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

import com.unla.grupo1.dtos.NotaPedidoDTO;
import com.unla.grupo1.entities.NotaPedido;
import com.unla.grupo1.helpers.ViewRouteHelper;
import com.unla.grupo1.services.INotaPedidoService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/notasPedido")
public class NotaPedidoController {

	@Autowired
	@Qualifier("notaPedidoService")
	private INotaPedidoService notaPedidoService;
	
	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping("")
	public ModelAndView NotasPedido() {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.NOTAS_PEDIDO);
		vista.addObject("notasPedido", notaPedidoService.getAll());

		return vista;
	}

	@GetMapping("/agregar/{producto}")
	public ModelAndView verAgregarNotaPedido(@PathVariable String producto) {

		ModelAndView vista = new ModelAndView(ViewRouteHelper.AGREGAR_NOTA_PEDIDO);
		vista.addObject("notaPedido", new NotaPedido());
		vista.addObject("producto", producto);

		return vista;
	}

	@PostMapping("/add")
	public String agregarNotaPedido(@ModelAttribute("notaPedido") NotaPedidoDTO notaPedidoDTO,
			RedirectAttributes message) {

		try {
			notaPedidoService.insertOrUpdate(notaPedidoDTO);
			message.addFlashAttribute("message", "La nota de pedido se generó exitosamente");
			return "redirect:/stock";

		} catch (Exception e) {
			message.addFlashAttribute("error", "No se pudo agregar la nota de pedido");
			return "redirect:/stock";
		}
	}

	@GetMapping("/inactiva/{id}")
	public String marcarInactiva(@PathVariable int id, RedirectAttributes message) {

		NotaPedidoDTO notaPedidoDTO = modelMapper.map(notaPedidoService.getById(id).get(), NotaPedidoDTO.class);
		notaPedidoDTO.setActiva(false);
		notaPedidoService.insertOrUpdate(notaPedidoDTO);
		message.addFlashAttribute("message", "La nota de pedido se marcó como inactiva");
		return "redirect:/notasPedido";
	}
}
