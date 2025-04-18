package apilist.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ApiListService {

	@Autowired
	ObjectMapper mapper = null;

	static final String JSON_CATEGORIES_JSON = "/json/category.json";
	static final String JSON_RESOURCES_JSON = "/json/resource.json";
}
