package apilist.controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/env")
public class EnvironmentController {
	@Autowired
	private ConfigurableEnvironment environment;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getEnvironment() {

		String segredo = "";
		try {
			segredo = new String(Files.readAllBytes(Paths.get("/run/secrets/api_key")));

		} catch (Exception e) {
			System.err.println("Erro ao ler o segredo: " + e.getMessage());
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("spring_env", environment.getSystemProperties());
		map.put("environment", System.getenv());
		map.put("properties", System.getProperties());
		map.put("secrets", segredo);

		return ResponseEntity.ok(map);
	}

}
