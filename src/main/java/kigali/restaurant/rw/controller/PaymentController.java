package kigali.restaurant.rw.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kigali.restaurant.rw.domain.Payment;
import kigali.restaurant.rw.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID foodOrderId, @RequestBody Payment payment) {
        String msg = paymentService.savePayment(foodOrderId, payment);
        if (msg.equals("The food order with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        if (msg.equals("Payments are only for SERVED food orders")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @GetMapping("/customer/{loyaltyNumber}")
    public List<Payment> byCustomer(@PathVariable String loyaltyNumber) {
        return paymentService.getByCustomer(loyaltyNumber);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = paymentService.deletePayment(id);
        if (msg.equals("The payment with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }
}