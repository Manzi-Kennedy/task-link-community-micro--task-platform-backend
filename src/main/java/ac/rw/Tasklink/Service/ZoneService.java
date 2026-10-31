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
        
        if (zone.getZoneNumber() == 0) {
            return "Zone number is required";
        }

        Optional<Zone> checkZone = zoneRepo.findByZoneNumber(zone.getZoneNumber());
        
        if (checkZone.isPresent()){
            return "The zone exist";
        }

        zoneRepo.save(zone);
        return "Zone saved successfully";
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
            return "The task with that id does not exist";
        }

        Zone updatedZone = existingZone.get();

        updatedZone.setName(zone.getName());
        updatedZone.setZoneNumber(zone.getZoneNumber());
        zoneRepo.save(updatedZone);
        return "Zone updated successfully";
    }

    public String deleteZone(UUID id){
        Optional<Zone> existzone = zoneRepo.findById(id);
        if (existzone.isEmpty()) {
            return "The task with that id does not exist";
        }

        zoneRepo.deleteById(id);
        return "Zone deleted successfully";
    }

    public List<Zone> getFreeZones(){
        return zoneRepo.findFreeZones();
    }
}
