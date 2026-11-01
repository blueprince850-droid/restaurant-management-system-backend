package auca.restaurant.rw.repository;
import auca.restaurant.rw.domain.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.util.*;
public interface PaymentRepository extends JpaRepository<Payment,UUID> { 
    boolean existsByFoodOrderId(UUID id); 
    @Query("SELECT p FROM Payment p WHERE p.foodOrder.customer.loyaltyNumber = :l ORDER BY p.paidDate DESC") List<Payment> findCustomer(@Param("l") String l); 
}
