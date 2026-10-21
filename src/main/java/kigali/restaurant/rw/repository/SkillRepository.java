package kigali.restaurant.rw.repository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.restaurant.rw.domain.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, UUID> {
    @Query("SELECT s FROM Skill s WHERE s.waiters IS EMPTY")
    java.util.List<Skill> findUnusedSkills();
}