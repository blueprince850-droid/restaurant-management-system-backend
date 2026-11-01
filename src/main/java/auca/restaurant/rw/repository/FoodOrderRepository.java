package auca.restaurant.rw.repository;
import auca.restaurant.rw.domain.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.time.LocalDate; import java.util.*;
public interface FoodOrderRepository extends JpaRepository<FoodOrder,UUID> {
    List<FoodOrder> findByStatusOrderByOrderDateAsc(OrderStatus s);
    List<FoodOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate a, LocalDate b);
    boolean existsByWaiterAndOrderDateAndStatusNot(Waiter w, LocalDate d, OrderStatus s);
    @Query("SELECT f.status, COUNT(f) FROM FoodOrder f GROUP BY f.status") List<Object[]> countByStatus();
    @Modifying @Query("UPDATE FoodOrder f SET f.status = 'CANCELLED' WHERE f.waiter.id = :w AND f.orderDate = :d AND f.status != 'SERVED'") int cancelDay(@Param("w") UUID w, @Param("d") LocalDate d);
}
