package ac.rw.Tasklink.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Rating;
import ac.rw.Tasklink.Domain.Task;

@Repository 
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    @Query ("SELECT r FROM Rating r WHERE r.task.poster.username = :username ORDER BY r.ratedDate DESC")
    List<Rating> findByPosterUsername(@Param("username") String username);

    boolean existsByTask(Task task);
}
