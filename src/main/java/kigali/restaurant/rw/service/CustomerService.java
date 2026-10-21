package kigali.restaurant.rw.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.restaurant.rw.domain.Customer;
import kigali.restaurant.rw.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public String saveCustomer(Customer customer) {
        customerRepo.save(customer);
        return "Customer saved successfully";
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(UUID id) {
        return customerRepo.findById(id).orElse(null);
    }

    public String updateCustomer(UUID id, Customer updated) {
        if (!customerRepo.existsById(id)) return "The customer with that id does not exist";
        updated.setId(id);
        customerRepo.save(updated);
        return "Customer updated successfully";
    }

    public String deleteCustomer(UUID id) {
        if (!customerRepo.existsById(id)) return "The customer with that id does not exist";
        customerRepo.deleteById(id);
        return "Customer deleted successfully";
    }

    public java.util.List<Customer> getByLastName(String lastName) {
        return customerRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}