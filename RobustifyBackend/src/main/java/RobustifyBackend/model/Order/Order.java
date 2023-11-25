package RobustifyBackend.model.Order;

import RobustifyBackend.model.Contacts.Contacts;
import RobustifyBackend.model.Contacts.EContacts;
import RobustifyBackend.model.Inventory.Items;
import RobustifyBackend.model.Purchases.Status;
import RobustifyBackend.model.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Items item;

    @NotNull
    private Integer quantity;

    @NotNull
    private double width;

    @NotNull
    private double height;

    @OneToOne
    @JoinColumn(name = "userId")
    private User assign_to;

    @NotNull
    private LocalDate deadline;

    @NotNull
    private double consumptions;

    @NotNull
    private double cost;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "customerID")
    private Contacts customer;


    public Order(){

    }

    public Order(Items item, Integer quantity, double width, double height, User assign_to, LocalDate deadline, double consumptions, double cost, Status status, Contacts customer) {
        this.item = item;
        this.quantity = quantity;
        this.width = width;
        this.height = height;
        this.assign_to = assign_to;
        this.deadline = deadline;
        this.consumptions = consumptions;
        this.cost = cost;
        this.status = status;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public User getAssign_to() {
        return assign_to;
    }

    public void setAssign_to(User assign_to) {
        this.assign_to = assign_to;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public double getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(double consumptions) {
        this.consumptions = consumptions;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Contacts getCustomer() {
        return customer;
    }

    public void setCustomer(Contacts customer) {
        this.customer = customer;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }
}
