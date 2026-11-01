package auca.restaurant.rw.repository;
import auca.restaurant.rw.domain.*; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface DiningTableRepository extends JpaRepository<DiningTable,UUID> { 
    @Query("SELECT d FROM DiningTable d WHERE NOT EXISTS (SELECT w FROM Waiter w WHERE w.diningTable = d) ORDER BY d.tableNumber") List<DiningTable> findFree(); 
    DiningTable findByTableNumber(int n); 
}
