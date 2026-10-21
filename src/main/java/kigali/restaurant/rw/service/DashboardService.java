package kigali.restaurant.rw.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.restaurant.rw.domain.OrderStatus;
import kigali.restaurant.rw.repository.CustomerRepository;
import kigali.restaurant.rw.repository.DiningTableRepository;
import kigali.restaurant.rw.repository.FoodOrderRepository;
import kigali.restaurant.rw.repository.PaymentRepository;
import kigali.restaurant.rw.repository.WaiterRepository;

@Service
public class DashboardService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private WaiterRepository waiterRepo;

    @Autowired
    private DiningTableRepository diningTableRepo;

    @Autowired
    private FoodOrderRepository foodOrderRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    public Map<String, Long> getSummary() {
        Map<String, Long> m = new LinkedHashMap<>();
        m.put("customers", customerRepo.count());
        m.put("waiters", waiterRepo.count());
        m.put("freeTables", (long) diningTableRepo.findFreeTables().size());
        m.put("ordersToday", foodOrderRepo.countByOrderDate(LocalDate.now()));
        m.put("placed", foodOrderRepo.countByStatus(OrderStatus.PLACED));
        m.put("payments", paymentRepo.count());
        return m;
    }
}