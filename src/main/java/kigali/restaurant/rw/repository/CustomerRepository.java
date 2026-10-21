package kigali.restaurant.rw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.restaurant.rw.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    java.util.List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    @Query("SELECT DISTINCT o.customer FROM FoodOrder o WHERE o.waiter.id = :waiterId")
    java.util.List<Customer> findCustomersOfWaiter(@Param("waiterId") java.util.UUID waiterId);
}