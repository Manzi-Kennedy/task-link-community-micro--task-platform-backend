package ac.rw.Tasklink.Domain;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "skill")
public class Skill {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany (mappedBy = "skills")
    @JsonIgnore 
    private List<Helper> helpers;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Helper> getHelpers() {
		return helpers;
	}

	public void setHelpers(List<Helper> helpers) {
		this.helpers = helpers;
	}

    
}
