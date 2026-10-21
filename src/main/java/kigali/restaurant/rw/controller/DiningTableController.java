package kigali.restaurant.rw.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kigali.restaurant.rw.domain.DiningTable;
import kigali.restaurant.rw.service.DiningTableService;

@RestController
@RequestMapping("/api/dining-tables")
public class DiningTableController {

    @Autowired
    private DiningTableService diningTableService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody DiningTable diningTable) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diningTableService.saveDiningTable(diningTable));
    }

    @GetMapping("/all")
    public List<DiningTable> getAll() {
        return diningTableService.getAllDiningTables();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        DiningTable t = diningTableService.getDiningTableById(id);
        if (t == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The dining table with that id does not exist");
        return ResponseEntity.ok(t);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody DiningTable t) {
        String msg = diningTableService.updateDiningTable(id, t);
        if (msg.equals("The dining table with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = diningTableService.deleteDiningTable(id);
        if (msg.equals("The dining table with that id does not exist")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }
    @GetMapping("/free")
    public List<DiningTable> free() {
        return diningTableService.getFreeTables();
    }
}