package ac.rw.Tasklink.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.rw.Tasklink.Domain.Task;

public interface TaskRepository extends JpaRepository<Task, UUID>{
    Optional<Task> findByTitle(String title);
}
