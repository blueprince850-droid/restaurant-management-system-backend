package kigali.restaurant.rw.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.restaurant.rw.domain.DiningTable;
import kigali.restaurant.rw.repository.DiningTableRepository;

@Service
public class DiningTableService {

    @Autowired
    private DiningTableRepository diningTableRepo;

    public String saveDiningTable(DiningTable diningTable) {
        diningTableRepo.save(diningTable);
        return "Dining table saved successfully";
    }

    public List<DiningTable> getAllDiningTables() {
        return diningTableRepo.findAll();
    }

    public DiningTable getDiningTableById(UUID id) {
        return diningTableRepo.findById(id).orElse(null);
    }

    public String updateDiningTable(UUID id, DiningTable updated) {
        if (!diningTableRepo.existsById(id)) return "The dining table with that id does not exist";
        updated.setId(id);
        diningTableRepo.save(updated);
        return "Dining table updated successfully";
    }

    public String deleteDiningTable(UUID id) {
        if (!diningTableRepo.existsById(id)) return "The dining table with that id does not exist";
        diningTableRepo.deleteById(id);
        return "Dining table deleted successfully";
    }
}