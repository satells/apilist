package apilist.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import apilist.dto.ResourceWithCategoryIdDTO;
import apilist.services.ResourceService;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

	@Autowired
	private ResourceService resourceService;

	@GetMapping
	public ResponseEntity<List<ResourceWithCategoryIdDTO>> getAll()
			throws StreamReadException, DatabindException, IOException {
		return ResponseEntity.ok(resourceService.getAll());
	}

	@GetMapping("/{category}")
	public ResponseEntity<List<ResourceWithCategoryIdDTO>> getByCategory(@PathVariable String category)
			throws StreamReadException, DatabindException, IOException {
		return ResponseEntity.ok(resourceService.getByCategory(category));
	}
}
