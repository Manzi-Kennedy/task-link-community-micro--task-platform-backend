package ac.rw.Tasklink.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Zone;

@Repository 
public interface ZoneRepository extends JpaRepository<Zone, UUID>{
    
    Optional<Zone> findByZoneNumberAndName(Integer ZoneNumber, String name);
}
