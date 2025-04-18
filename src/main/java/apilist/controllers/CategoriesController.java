package apilist.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import apilist.model.Category;
import apilist.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> getAll() throws StreamReadException, DatabindException, IOException {

		return ResponseEntity.ok(categoryService.getFindAll());
	}
}