package apilist.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class Config {

	@Bean
	ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

}
