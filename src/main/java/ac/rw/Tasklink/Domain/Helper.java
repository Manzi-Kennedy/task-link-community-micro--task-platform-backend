package ac.rw.Tasklink.Domain;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "helper")
public class Helper {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne 
    @JoinColumn (name = "zone_id", nullable = true)
    private Zone zone;

    @ManyToMany 
    @JoinTable (name = "helper_skill",
        joinColumns = @JoinColumn (name = "helper_id"),
        inverseJoinColumns = @JoinColumn (name = "skill_id")
    )
    private List<Skill> skills;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

    
    
}
