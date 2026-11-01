package auca.restaurant.rw.controller;
import auca.restaurant.rw.service.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/dashboard") public class DashboardController {
    private final DashboardService s; public DashboardController(DashboardService s){this.s=s;}
    @GetMapping("/summary") public Map<String,Long> a(){return s.get();}
}
