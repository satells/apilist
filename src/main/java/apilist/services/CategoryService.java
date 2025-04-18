package apilist.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import apilist.configuration.JsonInfo;
import apilist.model.Category;
import apilist.repository.CategoryRepository;

@Service
@Transactional(readOnly = true)
public class CategoryService extends ApiListService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Cacheable("categories-file")
	public List<Category> getFindAllFile() throws StreamReadException, DatabindException, IOException {
		JsonInfo<Category> JsonInfo = new JsonInfo<Category>(
				TypeReference.class.getResourceAsStream(JSON_CATEGORIES_JSON), new TypeReference<List<Category>>() {
				});
		return mapper.readValue(JsonInfo.getStream(), JsonInfo.getType());
	}

	@Cacheable("categories")
	public List<Category> getFindAll() {

		return categoryRepository.findAllCategory();
	}
}
