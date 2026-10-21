package kigali.restaurant.rw.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.restaurant.rw.domain.Skill;
import kigali.restaurant.rw.repository.SkillRepository;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepo;

    public String saveSkill(Skill skill) {
        skillRepo.save(skill);
        return "Skill saved successfully";
    }

    public List<Skill> getAllSkills() {
        return skillRepo.findAll();
    }

    public Skill getSkillById(UUID id) {
        return skillRepo.findById(id).orElse(null);
    }

    public String updateSkill(UUID id, Skill updated) {
        if (!skillRepo.existsById(id)) return "The skill with that id does not exist";
        updated.setId(id);
        skillRepo.save(updated);
        return "Skill updated successfully";
    }

    public String deleteSkill(UUID id) {
        if (!skillRepo.existsById(id)) return "The skill with that id does not exist";
        skillRepo.deleteById(id);
        return "Skill deleted successfully";
    }
}