package auca.restaurant.rw.repository;
import auca.restaurant.rw.domain.*; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface CustomerRepository extends JpaRepository<Customer,UUID> { List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName); }
