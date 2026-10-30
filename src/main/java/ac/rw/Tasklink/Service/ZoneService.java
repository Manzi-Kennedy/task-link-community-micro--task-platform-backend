package ac.rw.Tasklink.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rw.Tasklink.Domain.Zone;
import ac.rw.Tasklink.Repository.ZoneRepository;

@Service 
public class ZoneService {
    
    @Autowired 
    private ZoneRepository zoneRepo;


    public String saveZone(Zone zone){
        
        if (zone.getName() == null || zone.getZoneNumber() == 0) {
            return "Zone name or zone number are required";
        }

        Optional<Zone> checkZone = zoneRepo.findByZoneNumberAndName(zone.getZoneNumber(), zone.getName());
        
        if (checkZone.isPresent()){
            return "The zone exist";
        }

        zoneRepo.save(zone);
        return "Zone is saved successfully";
    }

    public List<Zone> getAllZones(){
        return zoneRepo.findAll();
    }

    public Optional<Zone> getZoneById(UUID id){
        return zoneRepo.findById(id);
    }

    public String updateZone(Zone zone, UUID id){
        Optional<Zone> existingZone = zoneRepo.findById(id);
        if (existingZone.isEmpty()) {
            return "Zone not found";
        }

        Zone updatedZone = existingZone.get();

        updatedZone.setName(zone.getName());
        updatedZone.setZoneNumber(zone.getZoneNumber());
        return "Zone is updated successfully";
    }

    public String deleteZone(UUID id){
        Optional<Zone> existzone = zoneRepo.findById(id);
        if (existzone.isEmpty()) {
            return "Zone is not found";
        }

        zoneRepo.deleteById(id);
        return "The Zone is deleted successfully";
    }
}
