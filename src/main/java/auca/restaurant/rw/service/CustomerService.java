package auca.restaurant.rw.service;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class CustomerService {
    private final CustomerRepository r; public CustomerService(CustomerRepository r){this.r=r;}
    public Customer s(Customer c){return r.save(c);} public List<Customer> a(){return r.findAll();}
    public Optional<Customer> f(UUID i){return r.findById(i);} public void d(UUID i){r.deleteById(i);}
    public List<Customer> ln(String n){return r.findByLastNameIgnoreCaseOrderByFirstNameAsc(n);}
}
