package ac.rw.Tasklink.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Zone;

@Repository 
public interface ZoneRepository extends JpaRepository<Zone, UUID>{
    
    Optional<Zone> findByZoneNumber(Integer ZoneNumber);

    @Query("SELECT z FROM Zone z WHERE NOT EXISTS (SELECT h FROM Helper h WHERE h.zone = z) ORDER BY z.zoneNumber ASC")
    List<Zone> findFreeZones();
}
