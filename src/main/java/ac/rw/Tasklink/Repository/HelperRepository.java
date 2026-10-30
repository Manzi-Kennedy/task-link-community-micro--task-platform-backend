package ac.rw.Tasklink.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Helper;

@Repository 
public interface HelperRepository extends JpaRepository<Helper, UUID> {
    Optional<Helper> findByFirstNameAndLastNameAndPhone(String firstName, String lastName, String phone);
}
