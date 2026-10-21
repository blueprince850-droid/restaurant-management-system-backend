package kigali.restaurant.rw.repository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.restaurant.rw.domain.DiningTable;

@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable, UUID> {

    Optional<DiningTable> findByTableNumber(int tableNumber);
    @Query("SELECT t FROM DiningTable t WHERE NOT EXISTS (SELECT w FROM Waiter w WHERE w.diningTable = t) ORDER BY t.tableNumber")
    java.util.List<DiningTable> findFreeTables();
}