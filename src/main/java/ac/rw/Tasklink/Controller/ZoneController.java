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

import ac.rw.Tasklink.Domain.Zone;
import ac.rw.Tasklink.Service.ZoneService;

@RestController 
@RequestMapping (value = "/api/zones")
public class ZoneController {

    @Autowired 
    private ZoneService zoneServe;

    @PostMapping (value = "/save")
    public ResponseEntity<?> saveZone(@RequestBody Zone zone){
        String returnedMessage = zoneServe.saveZone(zone);
        if (returnedMessage.equals("Zone saved successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @GetMapping (value = "/all")
    public ResponseEntity<?> getAllZone(){
           return new ResponseEntity<>(zoneServe.getAllZones(), HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<?> getZoneById(@PathVariable UUID id){
        Optional<Zone> zone = zoneServe.getZoneById(id);

        if (zone.isPresent()) {
            return new ResponseEntity<>(zone.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>("The task with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value = "/update/{id}")
    public ResponseEntity<?> updateZone(@PathVariable UUID id, @RequestBody Zone zone){

        String returnedMessage = zoneServe.updateZone(zone, id);

        if (returnedMessage.equals("Zone updated successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } else if (returnedMessage.equals("The task with that id does not exist")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<?> deleteZone(@PathVariable UUID id){

        String returnedMessage = zoneServe.deleteZone(id);

        if (returnedMessage.equals("Zone deleted successfully")) {
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    //C2
    @GetMapping (value = "/free")
    public ResponseEntity<?> getFreeZones(){
        return new ResponseEntity<>(zoneServe.getFreeZones(), HttpStatus.OK);
    }
}
