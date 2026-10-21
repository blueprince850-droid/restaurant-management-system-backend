package kigali.restaurant.rw.repository;

import org.springframework.data.repository.query.Param;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.restaurant.rw.domain.FoodOrder;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, UUID> {

    java.util.List<FoodOrder> findByStatusOrderByOrderDateAsc(kigali.restaurant.rw.domain.OrderStatus status);

    java.util.List<FoodOrder> findByOrderDateBetweenOrderByOrderDateAsc(java.time.LocalDate start, java.time.LocalDate end);
    boolean existsByWaiterIdAndOrderDateAndStatusNot(UUID waiterId, java.time.LocalDate orderDate, kigali.restaurant.rw.domain.OrderStatus status);
}