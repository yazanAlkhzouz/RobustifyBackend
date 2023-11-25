package RobustifyBackend.model.Inventory;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Items item;

    private Integer size;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String location;

    public Inventory() {
    }

    public Inventory(Items item,Integer size ,Integer quantity, String location) {
        this.item = item;
        this.size = size;
        this.quantity = quantity;
        this.location = location;
    }
    public void reduceQuantity(Integer quantity) {
        if (this.quantity < quantity) {
            throw new RuntimeException("Not enough inventory");
        }
        this.quantity -= quantity;
    }

    public void increaseQuantity(Integer quantity) {
        this.quantity += quantity;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Items getItems() {
        return item;
    }

    public void setItems(Items item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
