package ac.rw.Tasklink.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rw.Tasklink.Domain.Poster;
import ac.rw.Tasklink.Repository.PosterRepository;

@Service 
public class PosterService {
    
    @Autowired 
    private PosterRepository posterRepo;

    public String savePoster(Poster poster){
        Optional<Poster> checkPoster = posterRepo.findByUsername(poster.getUsername());

        if (checkPoster.isPresent()) {
            return "The username exists";
        }

        posterRepo.save(poster);
        return "Poster saved successfully";
    }

    public List<Poster> getAllPosters(){
        return posterRepo.findAll();
    }

    public Optional<Poster> getPosterById(UUID id){
        return posterRepo.findById(id);
    }

    public String updatePoster(Poster poster, UUID id){
        Optional<Poster> existPoster = posterRepo.findById(id);
        if (existPoster.isEmpty()){
            return "The task with that id does not exist";
        }

        Poster updatedPoster = existPoster.get();

        updatedPoster.setFirstName(poster.getFirstName());
        updatedPoster.setLastName(poster.getLastName());
        updatedPoster.setUsername(poster.getUsername());
        updatedPoster.setPhone(poster.getPhone());
        posterRepo.save(updatedPoster);
        return "Poster updated successfully";
    }

    public String deletePoster(UUID id){
        Optional<Poster> existingPoster = posterRepo.findById(id);
        if (existingPoster.isEmpty()) {
            return "The task with that id does not exist";
        }

        posterRepo.deleteById(id);
        return "Poster deleted successfully";
    }

    public List<Poster> getPosterByLastName(String lastName){
        return posterRepo.findByLastNameIgnoreCaseOrderByFirstName(lastName);
    }

        public List<Poster> getPostersOfHelper(UUID helperId){
        return posterRepo.findPostersByHelper(helperId);
    }
}
