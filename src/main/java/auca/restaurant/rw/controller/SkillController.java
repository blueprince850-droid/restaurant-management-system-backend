package auca.restaurant.rw.controller;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.service.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/skills") public class SkillController {
    private final SkillService s; public SkillController(SkillService s){this.s=s;}
    @PostMapping("/save") public ResponseEntity<?> a(@RequestBody Skill k){return new ResponseEntity<>(s.s(k),HttpStatus.CREATED);}
    @GetMapping("/all") public List<Skill> b(){return s.a();}
    @GetMapping("/{id}") public ResponseEntity<?> c(@PathVariable UUID i){Optional<Skill> o=s.f(i); if(o.isEmpty()) return new ResponseEntity<>("The skill with that id does not exist",HttpStatus.NOT_FOUND); return new ResponseEntity<>(o.get(),HttpStatus.OK);}
    @PutMapping("/update/{id}") public ResponseEntity<?> d(@PathVariable UUID i,@RequestBody Skill k){if(s.f(i).isEmpty()) return new ResponseEntity<>("The skill with that id does not exist",HttpStatus.NOT_FOUND); k.setId(i); return new ResponseEntity<>(s.s(k),HttpStatus.OK);}
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> e(@PathVariable UUID i){if(s.f(i).isEmpty()) return new ResponseEntity<>("The skill with that id does not exist",HttpStatus.NOT_FOUND); s.d(i); return new ResponseEntity<>("Skill deleted successfully",HttpStatus.OK);}
    @GetMapping("/unused") public List<Skill> f(){return s.unused();}
}
