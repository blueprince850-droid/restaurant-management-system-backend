package kigali.restaurant.rw.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kigali.restaurant.rw.domain.FoodOrder;
import kigali.restaurant.rw.domain.OrderStatus;
import kigali.restaurant.rw.service.FoodOrderService;

@RestController
@RequestMapping("/api/food-orders")
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody FoodOrder foodOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodOrderService.saveFoodOrder(foodOrder));
    }

    @GetMapping("/all")
    public List<FoodOrder> getAll() {
        return foodOrderService.getAllFoodOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        FoodOrder o = foodOrderService.getFoodOrderById(id);
        if (o == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The food order with that id does not exist");
        return ResponseEntity.ok(o);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody FoodOrder o) {
        String msg = foodOrderService.updateFoodOrder(id, o);
        if (msg.equals("The food order with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = foodOrderService.deleteFoodOrder(id);
        if (msg.equals("The food order with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/by-status")
    public List<FoodOrder> byStatus(@RequestParam OrderStatus status) {
        return foodOrderService.getByStatus(status);
    }

    @GetMapping("/between")
    public List<FoodOrder> between(@RequestParam String start, @RequestParam String end) {
        return foodOrderService.getBetween(LocalDate.parse(start), LocalDate.parse(end));
    }
}