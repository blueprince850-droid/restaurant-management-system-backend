package kigali.restaurant.rw.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.restaurant.rw.domain.FoodOrder;
import kigali.restaurant.rw.domain.OrderStatus;
import kigali.restaurant.rw.domain.Payment;
import kigali.restaurant.rw.repository.FoodOrderRepository;
import kigali.restaurant.rw.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private FoodOrderRepository foodOrderRepo;

    public String savePayment(UUID foodOrderId, Payment payment) {
        Optional<FoodOrder> o = foodOrderRepo.findById(foodOrderId);
        if (o.isEmpty()) return "The food order with that id does not exist";
        FoodOrder fo = o.get();
        if (fo.getStatus() != OrderStatus.SERVED) return "Payments are only for SERVED food orders";
        payment.setPaidDate(LocalDate.now());
        payment.setFoodOrder(fo);
        paymentRepo.save(payment);
        return "Payment saved successfully";
    }

    public List<Payment> getByCustomer(String loyaltyNumber) {
        return paymentRepo.findByCustomerLoyalty(loyaltyNumber);
    }

    public String deletePayment(UUID id) {
        if (!paymentRepo.existsById(id)) return "The payment with that id does not exist";
        paymentRepo.deleteById(id);
        return "Payment deleted successfully";
    }
}