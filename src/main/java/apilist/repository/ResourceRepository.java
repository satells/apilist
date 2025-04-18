package apilist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apilist.dto.ResourceWithCategoryIdDTO;
import apilist.model.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

	@Query("SELECT new apilist.dto.ResourceWithCategoryIdDTO(r.id, r.api, r.description, r.auth, r.https, r.cors, r.link, r.category.id) FROM Resource r")
	public List<ResourceWithCategoryIdDTO> getAllResources();
}
