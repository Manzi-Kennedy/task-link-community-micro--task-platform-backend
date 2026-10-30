package ac.rw.Tasklink.Controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.rw.Tasklink.Domain.Task;
import ac.rw.Tasklink.Service.HelperService;
import ac.rw.Tasklink.Service.PosterService;
import ac.rw.Tasklink.Service.TaskService;

@RestController 
@RequestMapping (value = "/api/task")
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
        if (returnedMessage.equals("Task is saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if (returnedMessage.equals("Task not found")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else{
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
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value = "/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable UUID id, @RequestBody Task task) {
        String returnedMessage = taskServe.updateTask(task, id);

        if (returnedMessage.equals("Task is updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("Task not found")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<?> deleteHelper(@PathVariable UUID id) {
        String returnedMessage = taskServe.deleteTask(id);

        if (returnedMessage.equals("Task is deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
