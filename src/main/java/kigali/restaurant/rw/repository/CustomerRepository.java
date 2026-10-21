package kigali.restaurant.rw.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.restaurant.rw.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    java.util.List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}