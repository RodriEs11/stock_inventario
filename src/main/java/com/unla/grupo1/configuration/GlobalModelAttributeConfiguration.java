package com.unla.grupo1.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.unla.grupo1.helpers.ViewRouteHelper;

@ControllerAdvice
public class GlobalModelAttributeConfiguration {

	// OBTIENE EL NOMBRE DE USUARIO AUTENTICADO, PARA AGREGARLO AL MODEL Y MOSTRARLO
	// DESDE TODAS LAS VISTAS
	@ModelAttribute
	public void AddUserGlobalToModel(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) auth.getPrincipal();

			String username = userDetails.getUsername();

			String userAdminRole = auth.getAuthorities().stream().map(authorization -> authorization.getAuthority())
					.findFirst().orElse(ViewRouteHelper.ROLE_ADMIN);

			String userClienteRole = auth.getAuthorities().stream().map(authorization -> authorization.getAuthority())
					.findFirst().orElse(ViewRouteHelper.ROLE_CLIENTE);

			String userGeneralRole = auth.getAuthorities().stream().map(authorization -> authorization.getAuthority())
					.findFirst().orElse(ViewRouteHelper.ROLE_GENERAL);

			model.addAttribute("currentUser", username);
			model.addAttribute("userAdminRole", userAdminRole);
			model.addAttribute("userClienteRole", userClienteRole);
			model.addAttribute("userGeneralRole", userGeneralRole);

			// AGREGA LAS VARIABLES AL MODEL GLOBAL
			model.addAttribute("ROLE_ADMIN", ViewRouteHelper.ROLE_ADMIN);
			model.addAttribute("ROLE_CLIENTE", ViewRouteHelper.ROLE_CLIENTE);
			model.addAttribute("ROLE_GENERAL", ViewRouteHelper.ROLE_GENERAL);
		}
	}
}
