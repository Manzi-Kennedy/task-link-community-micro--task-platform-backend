package ac.rw.Tasklink.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rw.Tasklink.Domain.Skill;
import ac.rw.Tasklink.Repository.HelperRepository;
import ac.rw.Tasklink.Repository.SkillRepository;

@Service 
public class SkillService {
    

    @Autowired 
    private SkillRepository skillRepo;

    @Autowired 
    private HelperRepository helpRepo;

    public String saveSkill(Skill skill){
        Optional<Skill> checkSkill = skillRepo.findByName(skill.getName());
        if (checkSkill.isPresent()) {
            return  "The Skill already exist";
        }

      
        skillRepo.save(skill);
        return "Skill saved successfully";
    }

    public List<Skill> getAllSkills(){
        return skillRepo.findAll();
    }

    public Optional<Skill> getSkiillId(UUID id){
        return skillRepo.findById(id);
    }

    public String updateSkill(Skill skill, UUID id){
        Optional<Skill> existingskill = skillRepo.findById(id);
        if (existingskill.isEmpty()) {
            return "The task with that id does not exist";
        }

        Skill updatedSkill = existingskill.get();
        
        updatedSkill.setName(skill.getName());
        updatedSkill.setHelpers(skill.getHelpers());
        skillRepo.save(updatedSkill);
        return "Skill updated successfully";
    }

    public String deleteSkill(UUID id){
        Optional<Skill> existing = skillRepo.findById(id);
        if (existing.isEmpty()) {
            return "The task with that id does not exist";
        }
        skillRepo.deleteById(id);
        return "Skill deleted successfully";
    }
}
