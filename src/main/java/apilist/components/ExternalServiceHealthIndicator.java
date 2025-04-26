package apilist.components;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.client.RestTemplate;

//@Component
public class ExternalServiceHealthIndicator extends AbstractHealthIndicator {

	private final RestTemplate restTemplate;

	public ExternalServiceHealthIndicator(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		try {
			// Tenta acessar um endpoint do serviço externo
			String response = restTemplate.getForObject("https://apilist.local.com/health", String.class);
			if (response != null && response.contains("OK")) {
				builder.up().withDetail("message", "Serviço externo está saudável");
			} else {
				builder.down().withDetail("error", "Resposta inesperada do serviço externo: " + response);
			}
		} catch (Exception e) {
			builder.down(e);
		}
	}
}