package auca.restaurant.rw.service;
import auca.restaurant.rw.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class DashboardService {
    private final CustomerRepository cr; private final WaiterRepository wr; private final DiningTableRepository dr; private final FoodOrderRepository fr; private final PaymentRepository pr;
    public DashboardService(CustomerRepository c, WaiterRepository w, DiningTableRepository d, FoodOrderRepository f, PaymentRepository p){this.cr=c;this.wr=w;this.dr=d;this.fr=f;this.pr=p;}
    public Map<String, Long> get(){ 
        Map<String,Long> m=new LinkedHashMap<>(); 
        m.put("customers",cr.count()); m.put("waiters",wr.count()); 
        m.put("freeTables",(long)dr.findFree().size()); m.put("ordersToday",0L); 
        m.put("placed",(long)fr.findByStatusOrderByOrderDateAsc(auca.restaurant.rw.domain.OrderStatus.PLACED).size()); 
        m.put("payments",pr.count()); return m; 
    }
}
