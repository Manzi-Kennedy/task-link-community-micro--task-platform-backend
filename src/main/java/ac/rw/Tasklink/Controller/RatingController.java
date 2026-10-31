package ac.rw.Tasklink.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ac.rw.Tasklink.Domain.Rating;
import ac.rw.Tasklink.Service.RatingService;

@RestController 
@RequestMapping ("/api/ratings")
public class RatingController {
    
     @Autowired
    private RatingService ratingServe;

    //E1
    @PostMapping (value = "/save")
    public ResponseEntity<?> saveRating(@RequestBody Rating rating, @RequestParam UUID taskId){
        String returnedMessage = ratingServe.saveRating(rating, taskId);

        if (returnedMessage.equals("Rating saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        } else if (returnedMessage.equals("The task with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }
    }

    //E2
    @GetMapping (value = "/poster/{username}")
    public ResponseEntity<?> getRatingsByPoster(@PathVariable String username){
        return new ResponseEntity<>(ratingServe.getRatingsByPoster(username), HttpStatus.OK);
    }

    //E3
    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable UUID id){
        String returnedMessage = ratingServe.deleteRating(id);

        if (returnedMessage.equals("Rating deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
