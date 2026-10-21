package kigali.restaurant.rw.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kigali.restaurant.rw.domain.Waiter;
import kigali.restaurant.rw.service.WaiterService;

@RestController
@RequestMapping("/api/waiters")
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Waiter waiter) {
        return ResponseEntity.status(HttpStatus.CREATED).body(waiterService.saveWaiter(waiter));
    }

    @GetMapping("/all")
    public List<Waiter> getAll() {
        return waiterService.getAllWaiters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Waiter w = waiterService.getWaiterById(id);
        if (w == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The waiter with that id does not exist");
        return ResponseEntity.ok(w);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Waiter w) {
        String msg = waiterService.updateWaiter(id, w);
        if (msg.equals("The waiter with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = waiterService.deleteWaiter(id);
        if (msg.equals("The waiter with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }
    @GetMapping("/by-skill")
    public List<Waiter> bySkill(@RequestParam String name) {
        return waiterService.getBySkill(name);
    }
}