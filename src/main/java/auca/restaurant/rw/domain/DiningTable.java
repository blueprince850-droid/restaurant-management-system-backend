package auca.restaurant.rw.domain;
import jakarta.persistence.*; import java.util.UUID;
@Entity @Table(name="dining_table") public class DiningTable {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    private int tableNumber; private String name;
    public UUID getId(){return id;} public void setId(UUID id){this.id=id;}
    public int getTableNumber(){return tableNumber;} public void setTableNumber(int n){this.tableNumber=n;}
    public String getName(){return name;} public void setName(String n){this.name=n;}
}
