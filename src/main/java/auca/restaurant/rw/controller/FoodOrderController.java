package auca.restaurant.rw.controller;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.service.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.LocalDate; import java.util.*;
@RestController @RequestMapping("/api/food-orders") public class FoodOrderController {
    private final FoodOrderService s; public FoodOrderController(FoodOrderService s){this.s=s;}
    @PostMapping("/save") public ResponseEntity<?> a(@RequestBody FoodOrder o){String r=s.s(o); if(r.equals("Food order date cannot be in the past")) return new ResponseEntity<>(r,HttpStatus.BAD_REQUEST); if(r.equals("Waiter already has an open order on that date")) return new ResponseEntity<>(r,HttpStatus.CONFLICT); return new ResponseEntity<>(r,HttpStatus.CREATED);}
    @GetMapping("/all") public List<FoodOrder> b(){return s.a();}
    @GetMapping("/{id}") public ResponseEntity<?> c(@PathVariable UUID i){Optional<FoodOrder> o=s.f(i); if(o.isEmpty()) return new ResponseEntity<>("The food order with that id does not exist",HttpStatus.NOT_FOUND); return new ResponseEntity<>(o.get(),HttpStatus.OK);}
    @PutMapping("/update/{id}") public ResponseEntity<?> d(@PathVariable UUID i,@RequestBody FoodOrder o){if(s.f(i).isEmpty()) return new ResponseEntity<>("The food order with that id does not exist",HttpStatus.NOT_FOUND); o.setId(i); return new ResponseEntity<>(o,HttpStatus.OK);}
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> e(@PathVariable UUID i){if(s.f(i).isEmpty()) return new ResponseEntity<>("The food order with that id does not exist",HttpStatus.NOT_FOUND); s.d(i); return new ResponseEntity<>("Food order deleted successfully",HttpStatus.OK);}
    @GetMapping("/by-status") public List<FoodOrder> f(@RequestParam OrderStatus status){return this.s.st(status);}
    @GetMapping("/between") public List<FoodOrder> g(@RequestParam String start,@RequestParam String end){return s.dt(LocalDate.parse(start),LocalDate.parse(end));}
    @GetMapping("/stats/by-status") public List<Object[]> h(){return s.stat();}
    @PatchMapping("/cancel-day") public ResponseEntity<?> i(@RequestParam UUID waiterId,@RequestParam String date){return new ResponseEntity<>(s.cancel(waiterId,LocalDate.parse(date)),HttpStatus.OK);}
    @PatchMapping("/{id}/status") public ResponseEntity<?> j(@PathVariable UUID id,@RequestParam OrderStatus status){String r=this.s.stat(id,status); if(r.equals("The food order with that id does not exist")) return new ResponseEntity<>(r,HttpStatus.NOT_FOUND); if(r.startsWith("Cannot change")) return new ResponseEntity<>(r,HttpStatus.BAD_REQUEST); return new ResponseEntity<>(r,HttpStatus.OK);}
    @GetMapping("/page")
    public org.springframework.data.domain.Page<FoodOrder> page(@RequestParam int page, @RequestParam int size, @RequestParam String sort){
        String[] p = sort.split(",");
        org.springframework.data.domain.Sort.Direction dir = p.length>1 && p[1].equalsIgnoreCase("desc") ? org.springframework.data.domain.Sort.Direction.DESC : org.springframework.data.domain.Sort.Direction.ASC;
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by(dir, p[0]));
        return s.pageAll(pageable);
    }
}

