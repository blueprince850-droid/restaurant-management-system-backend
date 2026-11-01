package auca.restaurant.rw.service;
import auca.restaurant.rw.domain.*; import auca.restaurant.rw.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class WaiterService {
    private final WaiterRepository r; private final SkillRepository sr; private final DiningTableRepository dr;
    public WaiterService(WaiterRepository r, SkillRepository sr, DiningTableRepository dr){this.r=r;this.sr=sr;this.dr=dr;}
    public Waiter s(Waiter w){return r.save(w);} public List<Waiter> a(){return r.findAll();}
    public Optional<Waiter> f(UUID i){return r.findById(i);} public void d(UUID i){r.deleteById(i);}
    public List<Waiter> bs(String n){return r.findBySkillName(n);}
    public String add(UUID w, UUID s){
        Optional<Waiter> wo=r.findById(w); if(wo.isEmpty()) return "The waiter with that id does not exist";
        Optional<Skill> so=sr.findById(s); if(so.isEmpty()) return "That skill does not exist";
        Waiter wt=wo.get(); Skill sk=so.get(); if(wt.getSkills()!=null && wt.getSkills().contains(sk)) return "The waiter already has that skill";
        wt.getSkills().add(sk); r.save(wt); return "Skill added to the waiter";
    }
    public String table(UUID w, int t){
        Optional<Waiter> wo=r.findById(w); if(wo.isEmpty()) return "The waiter with that id does not exist";
        DiningTable dt=dr.findByTableNumber(t); if(dt==null) return "The dining table with that tableNumber does not exist";
        Waiter wt=wo.get(); wt.setDiningTable(dt); r.save(wt); return "Dining table changed successfully";
    }
}
