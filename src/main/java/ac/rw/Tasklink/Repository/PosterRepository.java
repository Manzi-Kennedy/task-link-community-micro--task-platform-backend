package ac.rw.Tasklink.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.rw.Tasklink.Domain.Poster;

public interface PosterRepository extends JpaRepository<Poster, UUID>{
    
}
