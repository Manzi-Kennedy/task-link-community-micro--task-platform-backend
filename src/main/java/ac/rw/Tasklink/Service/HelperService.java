package ac.rw.Tasklink.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rw.Tasklink.Domain.Helper;
import ac.rw.Tasklink.Domain.Skill;
import ac.rw.Tasklink.Domain.Zone;
import ac.rw.Tasklink.Repository.HelperRepository;
import ac.rw.Tasklink.Repository.SkillRepository;
import ac.rw.Tasklink.Repository.ZoneRepository;
import jakarta.transaction.Transactional;

@Service 
public class HelperService {

    @Autowired 
    private HelperRepository helpRepo;

    @Autowired 
    private ZoneRepository zoneRepo;

    @Autowired 
    private SkillRepository skillRepo;

    public String saveHelper(Helper helper) {

        Optional<Helper> checkHelper = helpRepo.findByFirstNameAndLastNameAndPhone(helper.getFirstName(), helper.getLastName(), helper.getPhone());
        if (checkHelper.isPresent()){
            return "Helper already exists";
        }


        helpRepo.save(helper);
        return "Helper saved successfully";
    }

    public List<Helper> getAllHelpers(){
        return helpRepo.findAll();
    }

    public Optional<Helper> getHelperById(UUID id){
        return helpRepo.findById(id);
    }

    public String updateHelper(Helper helper, UUID id){
        Optional<Helper> existhelp = helpRepo.findById(id);
        if (existhelp.isEmpty()) {
            return "The task with that id does not exist";
        }
        Helper updatedHelper = existhelp.get();


        updatedHelper.setFirstName(helper.getFirstName());
        updatedHelper.setLastName(helper.getLastName());
        updatedHelper.setPhone(helper.getPhone());
        updatedHelper.setZone(helper.getZone());
        updatedHelper.setSkills(helper.getSkills());
        helpRepo.save(updatedHelper);
        return "Helper updated successfully";
    }

    public String deleteHelper(UUID id){
        Optional<Helper> existing = helpRepo.findById(id);
        if (existing.isEmpty()) {
            return "The task with that id does not exist";
        }
        helpRepo.deleteById(id);
        return "Helper deleted successfully";
    }

    public List<Helper> getHelpersBySkill(String name){
        return helpRepo.findBySkillName(name);
    }

    @Transactional 
    public String addSkillToHelper(UUID helperId, UUID skillId){
        Optional<Helper> existHelper = helpRepo.findById(helperId);
        if (existHelper.isEmpty()) {
            return "The helper with that id does not exist";
        }

        Optional<Skill> existSkill = skillRepo.findById(skillId);
        if (existSkill.isEmpty()) {
            return "That skill does not exist";
        }

        Helper helper = existHelper.get();
        Skill skill = existSkill.get();

        if (helper.getSkills() == null) {
            helper.setSkills(new ArrayList<>());
        }

        boolean alreadyHas = helper.getSkills().stream()
                .anyMatch(s -> s.getId().equals(skill.getId()));

        if (alreadyHas) {
            return "The helper already has that skill";
        }

        helper.getSkills().add(skill);
        helpRepo.save(helper);
        return "Skill added to the helper";
    }

        public String changeHelperZone(UUID helperId, Integer zoneNumber){
        Optional<Helper> existHelper = helpRepo.findById(helperId);
        if (existHelper.isEmpty()) {
            return "The helper with that id does not exist";
        }

        Optional<Zone> existZone = zoneRepo.findByZoneNumber(zoneNumber);
        if (existZone.isEmpty()) {
            return "The zone with that zoneNumber does not exist";
        }

        Helper helper = existHelper.get();
        Zone zone = existZone.get();

        boolean alreadyInThatZone = helper.getZone() != null && helper.getZone().getId().equals(zone.getId());

        if (!alreadyInThatZone && helpRepo.existsByZone(zone)) {
            return "That zone is taken by another helper";
        }

        helper.setZone(zone);
        helpRepo.save(helper);
        return "Zone changed successfully";
    }
}
