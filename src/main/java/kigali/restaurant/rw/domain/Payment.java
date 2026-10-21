package kigali.restaurant.rw.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String method;

    private String note;

    private int amount;

    @Column(name = "paid_date")
    private LocalDate paidDate;

    @ManyToOne
    @JoinColumn(name = "food_order_id")
    private FoodOrder foodOrder;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public LocalDate getPaidDate() { return paidDate; }
    public void setPaidDate(LocalDate paidDate) { this.paidDate = paidDate; }

    public FoodOrder getFoodOrder() { return foodOrder; }
    public void setFoodOrder(FoodOrder foodOrder) { this.foodOrder = foodOrder; }
}