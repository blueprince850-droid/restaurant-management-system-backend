package auca.restaurant.rw.service;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.repository.*; import org.springframework.stereotype.Service; import java.time.LocalDate; import java.util.*;
@Service public class PaymentService {
    private final PaymentRepository r; private final FoodOrderRepository fr;
    public PaymentService(PaymentRepository r, FoodOrderRepository fr){this.r=r;this.fr=fr;}
    public String s(UUID id, Payment p){
        Optional<FoodOrder> o=fr.findById(id); if(o.isEmpty()) return "The food order with that id does not exist";
        if(o.get().getStatus()!=OrderStatus.SERVED) return "Payments are only for SERVED food orders";
        p.setFoodOrder(o.get()); p.setPaidDate(LocalDate.now()); r.save(p); return "Payment saved successfully";
    }
    public List<Payment> c(String l){return r.findCustomer(l);}
    public String d(UUID i){ if(!r.existsById(i)) return "The payment with that id does not exist"; r.deleteById(i); return "Payment deleted successfully"; }
}
