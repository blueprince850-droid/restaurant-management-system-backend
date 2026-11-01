package auca.restaurant.rw.service;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class SkillService {
    private final SkillRepository r; public SkillService(SkillRepository r){this.r=r;}
    public Skill s(Skill k){return r.save(k);} public List<Skill> a(){return r.findAll();}
    public Optional<Skill> f(UUID i){return r.findById(i);} public void d(UUID i){r.deleteById(i);}
    public List<Skill> unused(){return r.findUnused();}
}
