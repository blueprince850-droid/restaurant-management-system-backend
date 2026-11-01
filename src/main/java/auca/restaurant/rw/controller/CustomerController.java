package auca.restaurant.rw.controller;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.service.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/customers") public class CustomerController {
    private final CustomerService s; public CustomerController(CustomerService s){this.s=s;}
    @PostMapping("/save") public ResponseEntity<?> a(@RequestBody Customer c){return new ResponseEntity<>(s.s(c),HttpStatus.CREATED);}
    @GetMapping("/all") public List<Customer> b(){return s.a();}
    @GetMapping("/{id}") public ResponseEntity<?> c(@PathVariable("id") UUID i){Optional<Customer> o=s.f(i); if(o.isEmpty()) return new ResponseEntity<>("The customer with that id does not exist",HttpStatus.NOT_FOUND); return new ResponseEntity<>(o.get(),HttpStatus.OK);}
    @PutMapping("/update/{id}") public ResponseEntity<?> d(@PathVariable("id") UUID i,@RequestBody Customer c){if(s.f(i).isEmpty()) return new ResponseEntity<>("The customer with that id does not exist",HttpStatus.NOT_FOUND); c.setId(i); return new ResponseEntity<>(s.s(c),HttpStatus.OK);}
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> e(@PathVariable("id") UUID i){if(s.f(i).isEmpty()) return new ResponseEntity<>("The customer with that id does not exist",HttpStatus.NOT_FOUND); s.d(i); return new ResponseEntity<>("Customer deleted successfully",HttpStatus.OK);}
    @GetMapping("/by-last-name") public List<Customer> f(@RequestParam String lastName){return s.ln(lastName);}
}
