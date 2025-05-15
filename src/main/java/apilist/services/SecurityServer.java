package apilist.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import apilist.model.DadosUsuario;

@Service
public class SecurityServer {

	private static final DadosUsuario USUARIO_NAO_AUTENTICADO = new DadosUsuario("não autenticado", "não autenticado");

	public DadosUsuario getDadosUsuario() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (usuarioLogado(authentication)) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			String roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", "));

			return new DadosUsuario(authentication.getName(), roles);
		}

		return USUARIO_NAO_AUTENTICADO;
	}

	private boolean usuarioLogado(Authentication authentication) {
		return authentication != null && authentication.isAuthenticated();
	}
}