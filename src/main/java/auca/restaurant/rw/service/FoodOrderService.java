package auca.restaurant.rw.service;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.repository.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.time.LocalDate; import java.util.*;
@Service public class FoodOrderService {
    private final FoodOrderRepository r; public FoodOrderService(FoodOrderRepository r){this.r=r;}
    public String s(FoodOrder o){
        if(o.getOrderDate().isBefore(LocalDate.now())) return "Food order date cannot be in the past";
        if(o.getWaiter()!=null && r.existsByWaiterAndOrderDateAndStatusNot(o.getWaiter(),o.getOrderDate(),OrderStatus.CANCELLED)) return "Waiter already has an open order on that date";
        o.setStatus(OrderStatus.PLACED); r.save(o); return "Food order saved successfully";
    }
    public List<FoodOrder> a(){return r.findAll();} public Optional<FoodOrder> f(UUID i){return r.findById(i);}
    public void d(UUID i){r.deleteById(i);} public List<FoodOrder> st(OrderStatus s){return r.findByStatusOrderByOrderDateAsc(s);}
    public List<FoodOrder> dt(LocalDate a, LocalDate b){return r.findByOrderDateBetweenOrderByOrderDateAsc(a,b);}
    public List<Object[]> stat(){return r.countByStatus();}
    @Transactional public String cancel(UUID w, LocalDate d){int c=r.cancelDay(w,d); return c+" food orders cancelled";}
    public String stat(UUID i, OrderStatus n){
        Optional<FoodOrder> o=r.findById(i); if(o.isEmpty()) return "The food order with that id does not exist";
        FoodOrder f=o.get(); OrderStatus c=f.getStatus();
        if(c==OrderStatus.SERVED || c==OrderStatus.CANCELLED) return "Cannot change status from "+c+" to "+n;
        if(c==OrderStatus.PLACED && n!=OrderStatus.PREPARING && n!=OrderStatus.CANCELLED) return "Cannot change status from "+c+" to "+n;
        if(c==OrderStatus.PREPARING && n!=OrderStatus.SERVED && n!=OrderStatus.CANCELLED) return "Cannot change status from "+c+" to "+n;
        f.setStatus(n); r.save(f); return "Food order status updated successfully";
    }
}
