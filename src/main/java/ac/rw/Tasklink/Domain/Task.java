package ac.rw.Tasklink.Domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "task")
public class Task {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate taskDate;
    
    @Enumerated (EnumType.STRING)
    private TaskStatus status;

    private String title;
    private String location;

    @ManyToOne 
    @JoinColumn (name = "poster_id")
    private Poster poster;

    @ManyToOne 
    @JoinColumn (name = "helper_id")
    private Helper helper;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(LocalDate taskDate) {
		this.taskDate = taskDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Poster getPoster() {
		return poster;
	}

	public void setPoster(Poster poster) {
		this.poster = poster;
	}

	public Helper getHelper() {
		return helper;
	}

	public void setHelper(Helper helper) {
		this.helper = helper;
	}

    
}
