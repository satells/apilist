package apilist;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import apilist.controllers.CategoriesController;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ApilistApplicationTests {
	@Autowired
	private CategoriesController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void rest_ip() throws UnknownHostException {

		InetAddress localHost = Inet4Address.getLocalHost();
		Assertions.assertNotNull(controller);

		Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:8080/ip", String.class),
				localHost.getHostAddress());

	}

	@Test
	void rest_categories() {
		String categories = this.restTemplate.getForObject("http://localhost:8080/categories", String.class);
		Assertions.assertNotNull(categories);
		System.out.println(categories);
	}

	@Test
	void rest_resources() {
		String resources = this.restTemplate.getForObject("http://localhost:8080/resources", String.class);
		Assertions.assertNotNull(resources);

		System.out.println(resources);

	}
}