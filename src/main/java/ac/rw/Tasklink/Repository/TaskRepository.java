package ac.rw.Tasklink.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Task;
import ac.rw.Tasklink.Domain.TaskStatus;

@Repository 
public interface TaskRepository extends JpaRepository<Task, UUID>{
    Optional<Task> findByTitle(String title);

    List<Task> findByStatusOrderByTaskDate(TaskStatus status);
    List<Task> findByTaskDateBetweenOrderByTaskDate(LocalDate start, LocalDate end);

    boolean existsByHelperIdAndTaskDateAndStatusNot(UUID helperId, LocalDate taskDate, TaskStatus status);
}
