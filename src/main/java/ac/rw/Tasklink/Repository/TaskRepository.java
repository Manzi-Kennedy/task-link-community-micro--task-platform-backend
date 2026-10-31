package ac.rw.Tasklink.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Task;
import ac.rw.Tasklink.Domain.TaskStatus;

@Repository 
public interface TaskRepository extends JpaRepository<Task, UUID>{
    Optional<Task> findByTitle(String title);

    List<Task> findByStatusOrderByTaskDate(TaskStatus status);
    List<Task> findByTaskDateBetweenOrderByTaskDate(LocalDate start, LocalDate end);

    boolean existsByHelperIdAndTaskDateAndStatusNot(UUID helperId, LocalDate taskDate, TaskStatus status);
    @Query("SELECT t.status, COUNT(t) FROM Task t GROUP BY t.status")
    List<Object[]> countTasksByStatus();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Task t SET t.status = :newStatus WHERE t.helper.id = :helperId AND t.taskDate = :date AND t.status <> :doneStatus")
    int cancelTasksOfHelperOnDate(@Param("helperId") UUID helperId, @Param("date") LocalDate date, @Param("newStatus") TaskStatus newStatus, 
    @Param("doneStatus") TaskStatus doneStatus);

    
    long countByTaskDate(LocalDate taskDate);
    long countByStatus(TaskStatus status);
}
