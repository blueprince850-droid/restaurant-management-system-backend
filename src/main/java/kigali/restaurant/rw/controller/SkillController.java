package kigali.restaurant.rw.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kigali.restaurant.rw.domain.Skill;
import kigali.restaurant.rw.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Skill skill) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.saveSkill(skill));
    }

    @GetMapping("/all")
    public List<Skill> getAll() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Skill skill = skillService.getSkillById(id);
        if (skill == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The skill with that id does not exist");
        return ResponseEntity.ok(skill);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Skill skill) {
        String msg = skillService.updateSkill(id, skill);
        if (msg.equals("The skill with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = skillService.deleteSkill(id);
        if (msg.equals("The skill with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }
    @GetMapping("/unused")
    public List<Skill> unused() {
        return skillService.getUnusedSkills();
    }
}