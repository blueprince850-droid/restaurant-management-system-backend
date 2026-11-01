package auca.restaurant.rw.service;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class DiningTableService {
    private final DiningTableRepository r; public DiningTableService(DiningTableRepository r){this.r=r;}
    public DiningTable s(DiningTable t){return r.save(t);} public List<DiningTable> a(){return r.findAll();}
    public Optional<DiningTable> f(UUID i){return r.findById(i);} public void d(UUID i){r.deleteById(i);}
    public List<DiningTable> free(){return r.findFree();} public DiningTable tn(int n){return r.findByTableNumber(n);}
}
