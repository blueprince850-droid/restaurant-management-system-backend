package kigali.restaurant.rw.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.restaurant.rw.domain.Waiter;
import kigali.restaurant.rw.repository.WaiterRepository;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepo;

    public String saveWaiter(Waiter waiter) {
        waiterRepo.save(waiter);
        return "Waiter saved successfully";
    }

    public List<Waiter> getAllWaiters() {
        return waiterRepo.findAll();
    }

    public Waiter getWaiterById(UUID id) {
        return waiterRepo.findById(id).orElse(null);
    }

    public String updateWaiter(UUID id, Waiter updated) {
        if (!waiterRepo.existsById(id)) return "The waiter with that id does not exist";
        updated.setId(id);
        waiterRepo.save(updated);
        return "Waiter updated successfully";
    }

    public String deleteWaiter(UUID id) {
        if (!waiterRepo.existsById(id)) return "The waiter with that id does not exist";
        waiterRepo.deleteById(id);
        return "Waiter deleted successfully";
    }
}