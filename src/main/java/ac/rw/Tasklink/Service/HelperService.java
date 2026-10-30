package ac.rw.Tasklink.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rw.Tasklink.Domain.Helper;
import ac.rw.Tasklink.Repository.HelperRepository;
import ac.rw.Tasklink.Repository.SkillRepository;
import ac.rw.Tasklink.Repository.ZoneRepository;

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
            return "The Helper exists";
        }


        helpRepo.save(helper);
        return "The Helper is saved successfully";
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
            return "Helper not found";
        }
        Helper updatedHelper = existhelp.get();


        updatedHelper.setFirstName(helper.getFirstName());
        updatedHelper.setLastName(helper.getLastName());
        updatedHelper.setPhone(helper.getPhone());
        updatedHelper.setZone(helper.getZone());
        updatedHelper.setSkills(helper.getSkills());
        return "Helper is updated successfully";
    }

    public String deleteHelper(UUID id){
        Optional<Helper> existing = helpRepo.findById(id);
        if (existing.isEmpty()) {
            return "The Helper is not found";
        }
        helpRepo.deleteById(id);
        return "The Helper is deleted successfully";
    }

}
