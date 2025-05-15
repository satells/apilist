package apilist;

import java.net.Inet4Address;
import java.net.InetAddress;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@WithMockUser(username = "admin", roles = "USER")
@AutoConfigureMockMvc
public class ApilistApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void rest_ip() throws Exception {

		InetAddress localHost = Inet4Address.getLocalHost();

		mockMvc.perform(MockMvcRequestBuilders.get("/ip").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.content().string(localHost.getHostAddress()));

	}

	@Test
	void rest_categories() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/categories").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	void rest_resources() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/resources").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	public void deveRetornarMensagemComUsuarioAutenticado() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/resources").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@WithAnonymousUser
	public void deveRetornarNaoAutorizadoParaEndpointProtegido() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("http://localhost:8080/resources").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isFound());
	}

}