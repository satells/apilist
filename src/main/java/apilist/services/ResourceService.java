package apilist.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import apilist.configuration.JsonInfo;
import apilist.dto.ResourceWithCategoryIdDTO;
import apilist.model.Resource;
import apilist.repository.ResourceRepository;

@Service
@Transactional(readOnly = true)
public class ResourceService extends ApiListService {

	@Autowired
	private ResourceRepository resourceRepository;

	@Cacheable("resource-file")
	public List<Resource> getFindAllFile() throws StreamReadException, DatabindException, IOException {
		JsonInfo<Resource> JsonInfo = new JsonInfo<Resource>(
				TypeReference.class.getResourceAsStream(JSON_RESOURCES_JSON), new TypeReference<List<Resource>>() {
				});
		return mapper.readValue(JsonInfo.getStream(), JsonInfo.getType());

	}

	@Cacheable("resource")
	public List<ResourceWithCategoryIdDTO> getAll() {
		return resourceRepository.getAllResources();

	}

	@Cacheable("resourceByCategory")
	public List<ResourceWithCategoryIdDTO> getByCategory(String category)
			throws StreamReadException, DatabindException, IOException {
		List<ResourceWithCategoryIdDTO> resources = this.getAll();

		return resources.stream().filter(resource -> resource.getCategoryId().equals(category))
				.collect(Collectors.toList());
	}
}