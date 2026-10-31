package ac.rw.Tasklink.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rw.Tasklink.Domain.Rating;
import ac.rw.Tasklink.Domain.Task;
import ac.rw.Tasklink.Domain.TaskStatus;
import ac.rw.Tasklink.Repository.RatingRepository;
import ac.rw.Tasklink.Repository.TaskRepository;

@Service 
public class RatingService {
    @Autowired
    private RatingRepository ratingRepo;

    @Autowired
    private TaskRepository taskRepo;


    public String saveRating(Rating rating, UUID taskId){
        Optional<Task> existTask = taskRepo.findById(taskId);

        if (existTask.isEmpty()) {
            return "The task with that id does not exist";
        }

        Task task = existTask.get();

        if (task.getStatus() != TaskStatus.DONE) {
            return "Ratings are only for DONE tasks";
        }

        rating.setTask(task);
        rating.setRatedDate(LocalDate.now());
        ratingRepo.save(rating);
        return "Rating saved successfully";
    }

    
    public List<Rating> getRatingsByPoster(String username){
        return ratingRepo.findByPosterUsername(username);
    }

    
    public String deleteRating(UUID id){
        Optional<Rating> existingRating = ratingRepo.findById(id);

        if (existingRating.isEmpty()) {
            return "The rating with that id does not exist";
        }

        ratingRepo.deleteById(id);
        return "Rating deleted successfully";
    }
}