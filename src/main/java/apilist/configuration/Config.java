package apilist.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class Config {

	@Bean
	ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
