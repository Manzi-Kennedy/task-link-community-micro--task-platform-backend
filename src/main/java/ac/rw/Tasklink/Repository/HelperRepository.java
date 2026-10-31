package ac.rw.Tasklink.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Helper;

@Repository 
public interface HelperRepository extends JpaRepository<Helper, UUID> {
    Optional<Helper> findByFirstNameAndLastNameAndPhone(String firstName, String lastName, String phone);

    @Query ("SELECT DISTINCT h FROM Helper h JOIN h.skills s WHERE LOWER(s.name) = LOWER(:name")
    List<Helper> findBySkillName(@Param ("name") String name);
}
