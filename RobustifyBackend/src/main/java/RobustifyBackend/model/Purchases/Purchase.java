package RobustifyBackend.model.Purchases;

import RobustifyBackend.model.Contacts.Contacts;
import RobustifyBackend.model.Contacts.EContacts;
import RobustifyBackend.model.Inventory.Items;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Data
@Entity
@Table(name = "Purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Items item;


    @NotNull
    private Integer quantity;

    @NotNull
    private LocalDate purchase_date;

    @NotNull
    private LocalDate bookInDate;

    @NotNull
    private LocalDate arrivalDate;

    @Enumerated(EnumType.STRING)
    private Status status;


    @OneToOne
    @JoinColumn(name = "supplierId")
    private Contacts supplier;




    public Purchase(){

    }

    public Purchase(Items item, Integer quantity, LocalDate purchase_date, LocalDate bookInDate, LocalDate arrivalDate , Status status, Contacts supplier) {
        this.item = item;
        this.quantity = quantity;
        this.purchase_date = purchase_date;
        this.bookInDate = bookInDate;
        this.arrivalDate = arrivalDate;
        this.status = status;
        this.supplier = supplier;
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

    public LocalDate getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(LocalDate purchase_date) {
        this.purchase_date = purchase_date;
    }

    public LocalDate getBookInDate() {
        return bookInDate;
    }

    public void setBookInDate(LocalDate bookInDate) {
        this.bookInDate = bookInDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }


    public Contacts getSupplier() {
        return supplier;
    }

    public void setSupplier(Contacts supplier) {
        this.supplier = supplier;
    }

}
