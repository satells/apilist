package apilist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceWithCategoryIdDTO {
	private Integer id;
	private String api;
	private String description;
	private String auth;
	private String https;
	private String cors;
	private String link;
	private Integer categoryId;
}
