package auca.restaurant.rw.repository;
import auca.restaurant.rw.domain.*; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface SkillRepository extends JpaRepository<Skill,UUID> { @Query("SELECT s FROM Skill s WHERE s.waiters IS EMPTY") List<Skill> findUnused(); }
