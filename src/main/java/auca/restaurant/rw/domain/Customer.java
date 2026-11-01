package auca.restaurant.rw.domain;
import jakarta.persistence.*; import java.util.UUID;
@Entity @Table(name="customer") public class Customer {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    private String firstName; private String lastName;
    @Column(unique=true) private String loyaltyNumber; private String phone;
    public UUID getId(){return id;} public void setId(UUID id){this.id=id;}
    public String getFirstName(){return firstName;} public void setFirstName(String f){this.firstName=f;}
    public String getLastName(){return lastName;} public void setLastName(String l){this.lastName=l;}
    public String getLoyaltyNumber(){return loyaltyNumber;} public void setLoyaltyNumber(String l){this.loyaltyNumber=l;}
    public String getPhone(){return phone;} public void setPhone(String p){this.phone=p;}
}
