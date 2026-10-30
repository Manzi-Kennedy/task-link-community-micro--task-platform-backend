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

import ac.rw.Tasklink.Domain.Poster;
import ac.rw.Tasklink.Service.PosterService;

@RestController 
@RequestMapping (value = "/api/poster")
public class PosterController {
    
    @Autowired 
    private PosterService posterServe;

    @PostMapping (value = "/save")
    public ResponseEntity<?> savePoster(@RequestBody Poster poster){
        String returnedMessage = posterServe.savePoster(poster);
        if (returnedMessage.equals("Poster is saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if (returnedMessage.equals("Poster not found")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllPoster(){
        return new ResponseEntity<>(posterServe.getAllPosters(), HttpStatus.OK);
    }
    @GetMapping (value = "/{id}")
    public ResponseEntity<?> getPosterById(@PathVariable UUID id){
         Optional<Poster> poster = posterServe.getPosterById(id);

        if (poster.isPresent()) {
            return new ResponseEntity<>(poster.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Poster not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value = "/update/{id}")
    public ResponseEntity<?> updatePoster(@PathVariable UUID id, @RequestBody Poster poster) {
        String returnedMessage = posterServe.updatePoster(poster, id);

        if (returnedMessage.equals("Poster is updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("Poster not found")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<?> deletePoster(@PathVariable UUID id) {
        String returnedMessage = posterServe.deletePoster(id);

        if (returnedMessage.equals("Poster is deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
    
}
