package auca.restaurant.rw.domain;
import jakarta.persistence.*; import java.util.*; import java.util.UUID;
@Entity @Table(name="waiter") public class Waiter {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    private String firstName; private String lastName; private String phone;
    @ManyToOne @JoinColumn(name="dining_table_id") private DiningTable diningTable;
    @ManyToMany @JoinTable(name="waiter_skill",joinColumns=@JoinColumn(name="waiter_id"),inverseJoinColumns=@JoinColumn(name="skill_id")) private List<Skill> skills;
    public UUID getId(){return id;} public void setId(UUID id){this.id=id;}
    public String getFirstName(){return firstName;} public void setFirstName(String f){this.firstName=f;}
    public String getLastName(){return lastName;} public void setLastName(String l){this.lastName=l;}
    public String getPhone(){return phone;} public void setPhone(String p){this.phone=p;}
    public DiningTable getDiningTable(){return diningTable;} public void setDiningTable(DiningTable d){this.diningTable=d;}
    public List<Skill> getSkills(){return skills;} public void setSkills(List<Skill> s){this.skills=s;}
}
