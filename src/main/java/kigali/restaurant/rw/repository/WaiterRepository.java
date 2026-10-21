package kigali.restaurant.rw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.restaurant.rw.domain.Waiter;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter, UUID> {
    @Query("SELECT DISTINCT w FROM Waiter w JOIN w.skills s WHERE LOWER(s.name) = LOWER(:name)")
    java.util.List<Waiter> findBySkillNameIgnoreCase(@Param("name") String name);
}