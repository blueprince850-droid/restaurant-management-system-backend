package auca.restaurant.rw.repository;
import auca.restaurant.rw.domain.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.util.*;
public interface WaiterRepository extends JpaRepository<Waiter,UUID> { @Query("SELECT DISTINCT w FROM Waiter w JOIN w.skills s WHERE LOWER(s.name) = LOWER(:name)") List<Waiter> findBySkillName(@Param("name") String n); }
