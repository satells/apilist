package apilist.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity

				.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(requests -> requests

						.requestMatchers("*").authenticated()

						.requestMatchers("/admin").hasRole("ADMIN")

						.requestMatchers("/actuator").hasRole("ADMIN")

						.requestMatchers("/ola").permitAll()

						.requestMatchers("/error").permitAll()

						.anyRequest().authenticated()

				)

				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler()))

				.logout(logout -> logout

						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))

						.logoutSuccessUrl("/pagina-de-agradecimento")

						.invalidateHttpSession(true).deleteCookies("JSESSIONID")

						.permitAll()

				)

				.httpBasic(withDefaults());

		return httpSecurity.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		var admin = User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build();
		var user = User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build();

		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				response.sendRedirect("/error");
			}
		};
	}

}
