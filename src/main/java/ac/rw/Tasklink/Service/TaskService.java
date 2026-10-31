package ac.rw.Tasklink.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rw.Tasklink.Domain.Task;
import ac.rw.Tasklink.Domain.TaskStatus;
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

        if (task.getTaskDate() != null && task.getTaskDate().isBefore(LocalDate.now())) {
            return "Task date cannot be in the past";
        }

        if (task.getHelper() != null && task.getTaskDate() != null && taskRepo.existsByHelperIdAndTaskDateAndStatusNot(
                        task.getHelper().getId(), task.getTaskDate(), TaskStatus.CANCELLED)) {
            return "Helper already has a task on that date";
        }

        Optional<Task> checkTask = taskRepo.findByTitle(task.getTitle());

        if (checkTask.isPresent()) {
            return "The task exists";
        }

        task.setStatus(TaskStatus.OPEN);
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

    public List<Task> getByStatus(TaskStatus status){
        return taskRepo.findByStatusOrderByTaskDate(status);
    }

    public List<Task> getTaskBetween(LocalDate start, LocalDate end){
        return taskRepo.findByTaskDateBetweenOrderByTaskDate(start, end);
    }
    
    
    public String updateTaskStatus(UUID id, TaskStatus newStatus){
        Optional<Task> existTask = taskRepo.findById(id);

        if (existTask.isEmpty()) {
            return "The task with that id does not exist";
        }

        Task task = existTask.get();
        TaskStatus current = task.getStatus();

        boolean allowed =
                (current == TaskStatus.OPEN && (newStatus == TaskStatus.CLAIMED || newStatus == TaskStatus.CANCELLED))
             || (current == TaskStatus.CLAIMED && (newStatus == TaskStatus.DONE || newStatus == TaskStatus.CANCELLED));

        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }

        task.setStatus(newStatus);
        taskRepo.save(task);
        return "Task status updated successfully";
    }        
}
