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

import ac.rw.Tasklink.Domain.Skill;
import ac.rw.Tasklink.Service.SkillService;

@RestController 
@RequestMapping (value = "/api/skills")
public class SkillController {
    
    @Autowired 
    private SkillService skillServe;


    @PostMapping (value = "/save")
    public ResponseEntity<?> saveSkill(@RequestBody Skill skill){
        String returnedMessage = skillServe.saveSkill(skill);

        if (returnedMessage.equals("Skill saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

        @GetMapping (value = "/all")
        public ResponseEntity<?> getAllSkills(){
            return new ResponseEntity<>(skillServe.getAllSkills(), HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<?> getSkillsId(@PathVariable UUID id){
        Optional<Skill> skill = skillServe.getSkiillId(id);

        if (skill.isPresent()) {
            return new ResponseEntity<>(skill.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The task with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value = "/update/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable UUID id, @RequestBody Skill skill){
        String returnedMessage = skillServe.updateSkill(skill, id);

        if (returnedMessage.equals("Skill updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if (returnedMessage.equals("The task with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }
        @DeleteMapping (value = "/delete/{id}")
        public ResponseEntity<?> deleteSkill(@PathVariable UUID id) {
            String returnedMessage = skillServe.deleteSkill(id);

            if (returnedMessage.equals("Skill deleted successfully")) {
                return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
            }
        }

        //C3
        @GetMapping(value = "/unused")
        public ResponseEntity<?> getUnusedSkills(){
            return new ResponseEntity<>(skillServe.getUnusedSkills(), HttpStatus.OK);
        }
}
