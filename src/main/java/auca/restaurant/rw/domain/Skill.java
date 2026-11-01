package auca.restaurant.rw.domain;
import com.fasterxml.jackson.annotation.JsonIgnore; import jakarta.persistence.*; import java.util.*; import java.util.UUID;
@Entity @Table(name="skill") public class Skill {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    private String name;
    @ManyToMany(mappedBy="skills") @JsonIgnore private List<Waiter> waiters;
    public UUID getId(){return id;} public void setId(UUID id){this.id=id;}
    public String getName(){return name;} public void setName(String n){this.name=n;}
    public List<Waiter> getWaiters(){return waiters;} public void setWaiters(List<Waiter> w){this.waiters=w;}
}
