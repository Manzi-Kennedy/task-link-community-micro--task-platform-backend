package ac.rw.Tasklink.Controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ac.rw.Tasklink.Domain.Task;
import ac.rw.Tasklink.Domain.TaskStatus;
import ac.rw.Tasklink.Service.HelperService;
import ac.rw.Tasklink.Service.PosterService;
import ac.rw.Tasklink.Service.TaskService;

@RestController 
@RequestMapping (value = "/api/tasks")
public class TaskController {
    
    @Autowired 
    private TaskService taskServe;

    @Autowired 
    private HelperService HelpServe;

    @Autowired 
    private PosterService postServe;

    @PostMapping (value = "/save")
    public ResponseEntity<?> saveTask(@RequestBody Task task){
        String returnedMessage = taskServe.saveTask(task);
        if (returnedMessage.equals("Task saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }//D1 
        else if (returnedMessage.equals("Task date cannot be in the past")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllHelper(){
        return new ResponseEntity<>(taskServe.getAllTasks(), HttpStatus.OK);
    }
    @GetMapping (value = "/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable UUID id){
         Optional<Task> task = taskServe.getTasksById(id);

        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The task with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value = "/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable UUID id, @RequestBody Task task) {
        String returnedMessage = taskServe.updateTask(task, id);

        if (returnedMessage.equals("Task updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The task with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<?> deleteHelper(@PathVariable UUID id) {
        String returnedMessage = taskServe.deleteTask(id);

        if (returnedMessage.equals("Task deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    //B2
    @GetMapping (value = "/by-status")
    public ResponseEntity<?> getTasksByStatus(@RequestParam TaskStatus status){
        return new ResponseEntity<>(taskServe.getByStatus(status), HttpStatus.OK);
    }

    //B3
    @GetMapping (value = "/between")
    public ResponseEntity<?> getTasksBetween(@RequestParam String start, @RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(taskServe.getTaskBetween(startDate, endDate), HttpStatus.OK);
    }

    //D2
    @PatchMapping (value = "/{id}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable UUID id, @RequestParam TaskStatus status){
        String returnedMessage = taskServe.updateTaskStatus(id, status);

        if (returnedMessage.equals("Task status updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The task with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
