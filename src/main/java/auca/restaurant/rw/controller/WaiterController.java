package auca.restaurant.rw.controller;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.service.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/waiters") public class WaiterController {
    private final WaiterService s; public WaiterController(WaiterService s){this.s=s;}
    @PostMapping("/save") public ResponseEntity<?> a(@RequestBody Waiter w){return new ResponseEntity<>(s.s(w),HttpStatus.CREATED);}
    @GetMapping("/all") public List<Waiter> b(){return s.a();}
    @GetMapping("/{id}") public ResponseEntity<?> c(@PathVariable UUID i){Optional<Waiter> o=s.f(i); if(o.isEmpty()) return new ResponseEntity<>("The waiter with that id does not exist",HttpStatus.NOT_FOUND); return new ResponseEntity<>(o.get(),HttpStatus.OK);}
    @PutMapping("/update/{id}") public ResponseEntity<?> d(@PathVariable UUID i,@RequestBody Waiter w){if(s.f(i).isEmpty()) return new ResponseEntity<>("The waiter with that id does not exist",HttpStatus.NOT_FOUND); w.setId(i); return new ResponseEntity<>(s.s(w),HttpStatus.OK);}
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> e(@PathVariable UUID i){if(s.f(i).isEmpty()) return new ResponseEntity<>("The waiter with that id does not exist",HttpStatus.NOT_FOUND); s.d(i); return new ResponseEntity<>("Waiter deleted successfully",HttpStatus.OK);}
    @GetMapping("/by-skill") public List<Waiter> f(@RequestParam String name){return s.bs(name);}
    @PutMapping("/{w}/skills/{s}") public ResponseEntity<?> g(@PathVariable UUID w,@PathVariable UUID s){String r=this.s.add(w,s); if(r.equals("The waiter with that id does not exist")||r.equals("That skill does not exist")) return new ResponseEntity<>(r,HttpStatus.NOT_FOUND); if(r.equals("The waiter already has that skill")) return new ResponseEntity<>(r,HttpStatus.CONFLICT); return new ResponseEntity<>(r,HttpStatus.OK);}
    @PatchMapping("/{w}/dining-table") public ResponseEntity<?> h(@PathVariable UUID w,@RequestParam int tableNumber){String r=s.table(w,tableNumber); if(r.equals("The waiter with that id does not exist")||r.equals("The dining table with that tableNumber does not exist")) return new ResponseEntity<>(r,HttpStatus.NOT_FOUND); return new ResponseEntity<>(r,HttpStatus.OK);}
}
