package apilist.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import apilist.model.DadosUsuario;
import apilist.services.SecurityServer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
class SecuredController {

	private DadosUsuario dadosUsuario;
	@Autowired
	private SecurityServer securityServer;

	@GetMapping("/admin")
	Map<String, String> admin() {
		dadosUsuario = securityServer.getDadosUsuario();
		return Map.of("admin", dadosUsuario.getNomeUsuario());
	}

	@GetMapping("/")
	Map<String, String> hello() {
		dadosUsuario = securityServer.getDadosUsuario();
		return Map.of("user", dadosUsuario.getNomeUsuario(), "roles", dadosUsuario.getRole());
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		System.out.println("authentication: " + authentication);
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/pagina-de-agradecimento";

	}

	@GetMapping("/pagina-de-agradecimento")
	public String agradecimento() {
		return "agradecemos";
	}

	@GetMapping("/ola")
	public String ola() {
		return "ola";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}
}