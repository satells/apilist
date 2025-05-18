package apilist.actuator.custom;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * http://localhost:8080/actuator/custom?id=4878
 * 
 */

@Endpoint(id = "custom")
@Component
public class CustomActuatorEndpoint {

	@ReadOperation
	public Map<String, String> customEndpoint(String id) {

		Map<String, String> map = new HashMap<>(Map.of("id", id));

		return map;
	}
}
