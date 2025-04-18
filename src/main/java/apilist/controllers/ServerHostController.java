package apilist.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ip")
public class ServerHostController {

	@GetMapping
	public ResponseEntity<String> getServerHost() throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();

		return ResponseEntity.ok(ip.getHostAddress());
	}
}
