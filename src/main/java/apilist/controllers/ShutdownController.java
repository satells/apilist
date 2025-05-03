package apilist.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {

	private final ApplicationContext applicationContext;

	public ShutdownController(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@GetMapping("/actuator/shutdown")
	public void shutdownApp() {
		SpringApplication.exit(applicationContext, () -> 0);
	}
}