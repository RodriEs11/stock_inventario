package com.unla.grupo1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo1.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping("")
	public String index() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(Model model) {

		return ViewRouteHelper.INDEX;
	}
}
