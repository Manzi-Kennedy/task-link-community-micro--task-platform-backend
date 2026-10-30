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

        if (checkPoster.isEmpty()) {
            return "The username exists";
        }

        posterRepo.save(poster);
        return "The Poster is saved successfully";
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
            return "Poster not found";
        }

        Poster updatedPoster = existPoster.get();

        updatedPoster.setFirstName(poster.getFirstName());
        updatedPoster.setLastName(poster.getLastName());
        updatedPoster.setUsername(poster.getUsername());
        updatedPoster.setPhone(poster.getPhone());
        return "Poster is updated successfully";
    }

    public String deletePoster(UUID id){
        Optional<Poster> existingPoster = posterRepo.findById(id);
        if (existingPoster.isEmpty()) {
            return "The poster is not found";
        }

        posterRepo.deleteById(id);
        return "Poster is deleted successfully";
    }
}
