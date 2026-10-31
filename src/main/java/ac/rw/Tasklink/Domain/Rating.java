package ac.rw.Tasklink.Domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "rating")
public class Rating {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String reviewer;
    private String comment;
    private int score;
    private LocalDate ratedDate;

    @ManyToOne 
    @JoinColumn (name = "task_id")
    private Task task;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getRatedDate() {
        return ratedDate;
    }

    public void setRatedDate(LocalDate ratedDate) {
        this.ratedDate = ratedDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    
}
