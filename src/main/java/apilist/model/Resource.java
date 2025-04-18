package apilist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "resource")
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
	private Integer id;
	private String api;
	private String description;
	private String auth;
	private String https;
	private String cors;
	private String link;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

}
