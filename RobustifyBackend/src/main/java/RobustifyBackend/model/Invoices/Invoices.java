package RobustifyBackend.model.Invoices;

import RobustifyBackend.model.Order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "Invoices")
public class Invoices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_invoice")
    private Order order;

    private Integer invoice_number;

    @NotNull
    private double discount;

    @NotNull
    private double total_price;

    @NotNull
    private double grand_total;

    @NotNull
    private LocalDate invoice_date;

    public Invoices() {

    }

    public Invoices(Order order, int invoice_number ,double discount,double total_price, double grand_total, LocalDate invoice_date) {
        this.order = order;
        this.invoice_number = invoice_number;
        this.discount = discount;
        this.total_price = total_price;
        this.grand_total = grand_total;
        this.invoice_date = invoice_date;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public  double getDiscount(){return discount;}

    public void setDiscount(double discount){this.discount = discount;}
    public void setOrder(Order order) {
        this.order = order;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(double grand_total) {
        this.grand_total = grand_total;
    }

    public LocalDate getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(LocalDate invoice_date) {
        this.invoice_date = invoice_date;
    }

    public int getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(int invoice_number) {
        this.invoice_number = invoice_number;
    }
}
