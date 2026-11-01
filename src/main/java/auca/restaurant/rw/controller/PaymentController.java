package auca.restaurant.rw.controller;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.service.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/payments") public class PaymentController {
    private final PaymentService s; public PaymentController(PaymentService s){this.s=s;}
    @PostMapping("/save") public ResponseEntity<?> a(@RequestParam UUID foodOrderId,@RequestBody Payment p){String r=s.s(foodOrderId,p); if(r.equals("The food order with that id does not exist")) return new ResponseEntity<>(r,HttpStatus.NOT_FOUND); if(r.equals("Payments are only for SERVED food orders")) return new ResponseEntity<>(r,HttpStatus.BAD_REQUEST); return new ResponseEntity<>(r,HttpStatus.CREATED);}
    @GetMapping("/customer/{l}") public List<Payment> b(@PathVariable String l){return s.c(l);}
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> c(@PathVariable UUID id){String r=s.d(id); if(r.equals("The payment with that id does not exist")) return new ResponseEntity<>(r,HttpStatus.NOT_FOUND); return new ResponseEntity<>(r,HttpStatus.OK);}
}
