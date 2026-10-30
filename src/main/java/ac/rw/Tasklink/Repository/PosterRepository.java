package ac.rw.Tasklink.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Poster;

@Repository 
public interface PosterRepository extends JpaRepository<Poster, UUID>{
    Optional<Poster> findByUsername(String username);
}
