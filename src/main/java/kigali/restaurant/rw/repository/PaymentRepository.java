package kigali.restaurant.rw.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kigali.restaurant.rw.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    boolean existsByFoodOrderId(UUID foodOrderId);

    @Query("SELECT p FROM Payment p WHERE p.foodOrder.customer.loyaltyNumber = :loyalty ORDER BY p.paidDate DESC")
    List<Payment> findByCustomerLoyalty(@Param("loyalty") String loyalty);
}