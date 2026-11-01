package auca.restaurant.rw.controller;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.service.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/dining-tables") public class DiningTableController {
    private final DiningTableService s; public DiningTableController(DiningTableService s){this.s=s;}
    @PostMapping("/save") public ResponseEntity<?> a(@RequestBody DiningTable t){return new ResponseEntity<>(s.s(t),HttpStatus.CREATED);}
    @GetMapping("/all") public List<DiningTable> b(){return s.a();}
    @GetMapping("/{id}") public ResponseEntity<?> c(@PathVariable UUID i){Optional<DiningTable> o=s.f(i); if(o.isEmpty()) return new ResponseEntity<>("The dining table with that id does not exist",HttpStatus.NOT_FOUND); return new ResponseEntity<>(o.get(),HttpStatus.OK);}
    @PutMapping("/update/{id}") public ResponseEntity<?> d(@PathVariable UUID i,@RequestBody DiningTable t){if(s.f(i).isEmpty()) return new ResponseEntity<>("The dining table with that id does not exist",HttpStatus.NOT_FOUND); t.setId(i); return new ResponseEntity<>(s.s(t),HttpStatus.OK);}
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> e(@PathVariable UUID i){if(s.f(i).isEmpty()) return new ResponseEntity<>("The dining table with that id does not exist",HttpStatus.NOT_FOUND); s.d(i); return new ResponseEntity<>("Dining table deleted successfully",HttpStatus.OK);}
    @GetMapping("/free") public List<DiningTable> f(){return s.free();}
}
