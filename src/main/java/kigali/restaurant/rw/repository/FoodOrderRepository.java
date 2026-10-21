package kigali.restaurant.rw.repository;

import org.springframework.data.repository.query.Param;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kigali.restaurant.rw.domain.FoodOrder;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, UUID> {

    java.util.List<FoodOrder> findByStatusOrderByOrderDateAsc(kigali.restaurant.rw.domain.OrderStatus status);

    java.util.List<FoodOrder> findByOrderDateBetweenOrderByOrderDateAsc(java.time.LocalDate start, java.time.LocalDate end);
    boolean existsByWaiterIdAndOrderDateAndStatusNot(UUID waiterId, java.time.LocalDate orderDate, kigali.restaurant.rw.domain.OrderStatus status);

    @Query("SELECT o.status, COUNT(o) FROM FoodOrder o GROUP BY o.status")
    java.util.List<Object[]> countByStatusGroup();

    @Modifying
    @Query("UPDATE FoodOrder o SET o.status = kigali.restaurant.rw.domain.OrderStatus.CANCELLED WHERE o.waiter.id = :waiterId AND o.orderDate = :date AND o.status <> kigali.restaurant.rw.domain.OrderStatus.SERVED")
    int cancelDay(@Param("waiterId") java.util.UUID waiterId, @Param("date") java.time.LocalDate date);
}