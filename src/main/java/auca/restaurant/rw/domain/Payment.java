package auca.restaurant.rw.domain;
import jakarta.persistence.*; import java.time.LocalDate; import java.util.UUID;
@Entity @Table(name="payment") public class Payment {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    private String method; private String note; private int amount; private LocalDate paidDate;
    @ManyToOne @JoinColumn(name="food_order_id") private FoodOrder foodOrder;
    public UUID getId(){return id;} public void setId(UUID id){this.id=id;}
    public String getMethod(){return method;} public void setMethod(String m){this.method=m;}
    public String getNote(){return note;} public void setNote(String n){this.note=n;}
    public int getAmount(){return amount;} public void setAmount(int a){this.amount=a;}
    public LocalDate getPaidDate(){return paidDate;} public void setPaidDate(LocalDate d){this.paidDate=d;}
    public FoodOrder getFoodOrder(){return foodOrder;} public void setFoodOrder(FoodOrder f){this.foodOrder=f;}
}
