package ac.rw.Tasklink.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rw.Tasklink.Domain.Task;
import ac.rw.Tasklink.Repository.HelperRepository;
import ac.rw.Tasklink.Repository.PosterRepository;
import ac.rw.Tasklink.Repository.TaskRepository;

@Service 
public class TaskService {
    
    @Autowired 
    public TaskRepository taskRepo;

    @Autowired 
    public HelperRepository helpRepo;

    @Autowired 
    public PosterRepository postRepo;

    public String saveTask(Task task){
        Optional<Task> checkTask = taskRepo.findByTitle(task.getTitle());

        if (checkTask.isPresent()) {
            return "The task exists";
        }

        taskRepo.save(task);
        return "Task saved successfully";
    }

    public List<Task> getAllTasks(){
        return taskRepo.findAll();
    }

    public Optional<Task> getTasksById(UUID id){
        return taskRepo.findById(id);
    }

    public String updateTask(Task task, UUID id){
        Optional<Task> existtask = taskRepo.findById(id);

        if (existtask.isEmpty()){
            return "The task with that id does not exist";
        }

        Task updatedTask = existtask.get();

        updatedTask.setTitle(task.getTitle());
        updatedTask.setLocation(task.getLocation());
        updatedTask.setStatus(task.getStatus());
        updatedTask.setTaskDate(task.getTaskDate());
        updatedTask.setPoster(task.getPoster());
        updatedTask.setHelper(task.getHelper());
        taskRepo.save(updatedTask);
        return "Task updated successfully";
    }

    public String deleteTask(UUID id){
        Optional<Task> existingTask = taskRepo.findById(id);
        if (existingTask.isEmpty()) {
            return "The task with that id does not exist";
        }
        taskRepo.deleteById(id);
        return "Task deleted successfully";
    }
}
