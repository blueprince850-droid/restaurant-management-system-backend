package kigali.restaurant.rw.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.restaurant.rw.domain.FoodOrder;
import kigali.restaurant.rw.repository.FoodOrderRepository;

@Service
public class FoodOrderService {

    @Autowired
    private FoodOrderRepository foodOrderRepo;

    public String saveFoodOrder(FoodOrder foodOrder) {
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
        foodOrderRepo.deleteById(id);
        return "Food order deleted successfully";
    }

    public java.util.List<FoodOrder> getByStatus(kigali.restaurant.rw.domain.OrderStatus status) {
        return foodOrderRepo.findByStatusOrderByOrderDateAsc(status);
    }

    public java.util.List<FoodOrder> getBetween(java.time.LocalDate start, java.time.LocalDate end) {
        return foodOrderRepo.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }
}