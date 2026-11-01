package auca.restaurant.rw.domain;
import jakarta.persistence.*; import java.time.LocalDate; import java.util.UUID;
@Entity @Table(name="food_order") public class FoodOrder {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    private LocalDate orderDate; @Enumerated(EnumType.STRING) private OrderStatus status;
    private String items; private double totalAmount;
    @ManyToOne @JoinColumn(name="customer_id") private Customer customer;
    @ManyToOne @JoinColumn(name="waiter_id") private Waiter waiter;
    public UUID getId(){return id;} public void setId(UUID id){this.id=id;}
    public LocalDate getOrderDate(){return orderDate;} public void setOrderDate(LocalDate d){this.orderDate=d;}
    public OrderStatus getStatus(){return status;} public void setStatus(OrderStatus s){this.status=s;}
    public String getItems(){return items;} public void setItems(String i){this.items=i;}
    public double getTotalAmount(){return totalAmount;} public void setTotalAmount(double t){this.totalAmount=t;}
    public Customer getCustomer(){return customer;} public void setCustomer(Customer c){this.customer=c;}
    public Waiter getWaiter(){return waiter;} public void setWaiter(Waiter w){this.waiter=w;}
}
