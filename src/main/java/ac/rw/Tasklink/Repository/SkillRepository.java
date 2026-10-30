package ac.rw.Tasklink.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.rw.Tasklink.Domain.Skill;

@Repository 
public interface SkillRepository extends JpaRepository<Skill, UUID>{
       Optional<Skill>findByName(String name);
}
