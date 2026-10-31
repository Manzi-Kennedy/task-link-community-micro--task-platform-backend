package ac.rw.Tasklink.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Poster;

@Repository 
public interface PosterRepository extends JpaRepository<Poster, UUID>{
    Optional<Poster> findByUsername(String username);

    List<Poster> findByLastNameIgnoreCaseOrderByFirstName(String lastName);
    @Query ("SELECT DISTINCT t.poster FROM Task t WHERE t.helper.id = :helperId")
    List<Poster> findPostersByHelper(@Param ("helperId") UUID helperId);
}
