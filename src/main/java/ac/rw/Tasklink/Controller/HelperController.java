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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ac.rw.Tasklink.Domain.Helper;
import ac.rw.Tasklink.Service.HelperService;

@RestController 
@RequestMapping (value = "/api/helpers")
public class HelperController {
    @Autowired 
    private HelperService helpServe;

    @PostMapping (value = "/save")
    public ResponseEntity<?> saveHelper(@RequestBody Helper helper){
        String returnedMessage = helpServe.saveHelper(helper);
        if (returnedMessage.equals("Helper saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllHelper(){
        return new ResponseEntity<>(helpServe.getAllHelpers(), HttpStatus.OK);
    }
    @GetMapping (value = "/{id}")
    public ResponseEntity<?> getHelperById(@PathVariable UUID id){
         Optional<Helper> helper = helpServe.getHelperById(id);

        if (helper.isPresent()) {
            return new ResponseEntity<>(helper.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The task with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value = "/update/{id}")
    public ResponseEntity<?> updateHelper(@PathVariable UUID id, @RequestBody Helper helper) {
        String returnedMessage = helpServe.updateHelper(helper, id);

        if (returnedMessage.equals("Helper updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The task with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<?> deleteHelper(@PathVariable UUID id) {
        String returnedMessage = helpServe.deleteHelper(id);

        if (returnedMessage.equals("Helper deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    //C1
    @GetMapping (value = "/by-skill")
    public ResponseEntity<?> getHelpersBySkill(@RequestParam String name){
        return new ResponseEntity<>(helpServe.getHelpersBySkill(name), HttpStatus.OK);
    }
    
}
