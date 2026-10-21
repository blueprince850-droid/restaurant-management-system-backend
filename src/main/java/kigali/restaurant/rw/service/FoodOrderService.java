package kigali.restaurant.rw.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.restaurant.rw.domain.FoodOrder;
import kigali.restaurant.rw.repository.FoodOrderRepository;
import kigali.restaurant.rw.repository.PaymentRepository;

@Service
public class FoodOrderService {

    @Autowired
    private FoodOrderRepository foodOrderRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    public String saveFoodOrder(FoodOrder foodOrder) {
        java.time.LocalDate date = foodOrder.getOrderDate();
        if (date.isBefore(java.time.LocalDate.now())) return "Food order date cannot be in the past";
        if (foodOrder.getWaiter() != null && foodOrderRepo.existsByWaiterIdAndOrderDateAndStatusNot(foodOrder.getWaiter().getId(), date, kigali.restaurant.rw.domain.OrderStatus.CANCELLED)) {
            return "Waiter already has an open order on that date";
        }
        foodOrder.setStatus(kigali.restaurant.rw.domain.OrderStatus.PLACED);
        foodOrderRepo.save(foodOrder);
        return "Food order saved successfully";
    }

    public List<FoodOrder> getAllFoodOrders() {
        return foodOrderRepo.findAll();
    }

    public FoodOrder getFoodOrderById(UUID id) {
        return foodOrderRepo.findById(id).orElse(null);
    }

    public String updateFoodOrder(UUID id, FoodOrder updated) {
        if (!foodOrderRepo.existsById(id)) return "The food order with that id does not exist";
        updated.setId(id);
        foodOrderRepo.save(updated);
        return "Food order updated successfully";
    }

    public String deleteFoodOrder(UUID id) {
        if (!foodOrderRepo.existsById(id)) return "The food order with that id does not exist";
        if (paymentRepo.existsByFoodOrderId(id)) return "That food order has payments, delete them first";
        foodOrderRepo.deleteById(id);
        return "Food order deleted successfully";
    }

    public java.util.List<FoodOrder> getByStatus(kigali.restaurant.rw.domain.OrderStatus status) {
        return foodOrderRepo.findByStatusOrderByOrderDateAsc(status);
    }

    public java.util.List<FoodOrder> getBetween(java.time.LocalDate start, java.time.LocalDate end) {
        return foodOrderRepo.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }
    public String changeStatus(UUID id, kigali.restaurant.rw.domain.OrderStatus newStatus) {
        java.util.Optional<FoodOrder> existing = foodOrderRepo.findById(id);
        if (existing.isEmpty()) return "The food order with that id does not exist";
        FoodOrder o = existing.get();
        kigali.restaurant.rw.domain.OrderStatus current = o.getStatus();
        boolean ok = false;
        if (current == kigali.restaurant.rw.domain.OrderStatus.PLACED && (newStatus == kigali.restaurant.rw.domain.OrderStatus.PREPARING || newStatus == kigali.restaurant.rw.domain.OrderStatus.CANCELLED)) ok = true;
        if (current == kigali.restaurant.rw.domain.OrderStatus.PREPARING && (newStatus == kigali.restaurant.rw.domain.OrderStatus.SERVED || newStatus == kigali.restaurant.rw.domain.OrderStatus.CANCELLED)) ok = true;
        if (!ok) return "Cannot change status from " + current + " to " + newStatus;
        o.setStatus(newStatus);
        foodOrderRepo.save(o);
        return "Food order status updated successfully";
    }

    public java.util.List<Object[]> countByStatus() {
        return foodOrderRepo.countByStatusGroup();
    }

    @org.springframework.transaction.annotation.Transactional
    public String cancelDay(UUID waiterId, java.time.LocalDate date) {
        int n = foodOrderRepo.cancelDay(waiterId, date);
        return n + " food orders cancelled";
    }

    public org.springframework.data.domain.Page<FoodOrder> getPage(int page, int size, String field, org.springframework.data.domain.Sort.Direction dir) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by(dir, field));
        return foodOrderRepo.findAll(pageable);
    }
}