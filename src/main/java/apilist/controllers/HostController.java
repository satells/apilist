package apilist.controllers;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apilist.model.DockerHost;

@RestController
@RequestMapping("/host")
public class HostController {

	@GetMapping
	public ResponseEntity<DockerHost> getHost() throws UnknownHostException {

		InetAddress localHost = Inet4Address.getLocalHost();

		String hostAddress = localHost.getHostAddress();

		DockerHost host = DockerHost.builder().hostAddress(hostAddress).build();

		return ResponseEntity.ok(host);
	}
}
