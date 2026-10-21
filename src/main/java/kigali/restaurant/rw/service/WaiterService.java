package kigali.restaurant.rw.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.restaurant.rw.domain.Waiter;
import kigali.restaurant.rw.repository.WaiterRepository;
import kigali.restaurant.rw.repository.SkillRepository;
import kigali.restaurant.rw.repository.DiningTableRepository;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepo;

    @Autowired
    private SkillRepository skillRepo;

    @Autowired
    private DiningTableRepository diningTableRepo;

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
    public java.util.List<Waiter> getBySkill(String name) {
        return waiterRepo.findBySkillNameIgnoreCase(name);
    }
    public String addSkill(UUID waiterId, UUID skillId) {
        java.util.Optional<Waiter> wOpt = waiterRepo.findById(waiterId);
        if (wOpt.isEmpty()) return "The waiter with that id does not exist";
        java.util.Optional<kigali.restaurant.rw.domain.Skill> sOpt = skillRepo.findById(skillId);
        if (sOpt.isEmpty()) return "That skill does not exist";
        Waiter w = wOpt.get();
        kigali.restaurant.rw.domain.Skill s = sOpt.get();
        if (w.getSkills().contains(s)) return "The waiter already has that skill";
        w.getSkills().add(s);
        waiterRepo.save(w);
        return "Skill added to the waiter";
    }
    public String changeDiningTable(UUID waiterId, int tableNumber) {
        java.util.Optional<Waiter> wOpt = waiterRepo.findById(waiterId);
        if (wOpt.isEmpty()) return "The waiter with that id does not exist";
        java.util.Optional<kigali.restaurant.rw.domain.DiningTable> tOpt = diningTableRepo.findByTableNumber(tableNumber);
        if (tOpt.isEmpty()) return "The dining table with that tableNumber does not exist";
        kigali.restaurant.rw.domain.DiningTable t = tOpt.get();
        if (waiterRepo.existsByDiningTableIdAndIdNot(t.getId(), waiterId)) return "That dining table is taken by another waiter";
        Waiter w = wOpt.get();
        w.setDiningTable(t);
        waiterRepo.save(w);
        return "Dining table changed successfully";
    }
}