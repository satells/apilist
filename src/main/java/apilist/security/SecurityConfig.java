package apilist.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity

				.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(requests -> requests

						.requestMatchers("**").authenticated()

						.requestMatchers("/admin").hasRole("ADMIN")

						.requestMatchers("/ola").permitAll()

						.requestMatchers("/error").permitAll()

						.anyRequest().authenticated()

				)

				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler()))

				.formLogin(

						Customizer.withDefaults()

				)

				.httpBasic(httpBasic -> httpBasic.disable())

				.logout(logout -> logout

						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))

						.logoutSuccessUrl("/pagina-de-agradecimento")

						.invalidateHttpSession(true).deleteCookies("JSESSIONID")

						.permitAll()

				);

		return httpSecurity.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		var mauro = User.withUsername("admin").password("{noop}1234").roles("USER").build();
		var heitor = User.withUsername("usuario").password("{noop}1234").roles("USER", "ADMIN").build();

		return new InMemoryUserDetailsManager(mauro, heitor);
	}

	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				response.sendRedirect("/error"); // Redireciona para o seu endpoint
			}
		};
	}

}
