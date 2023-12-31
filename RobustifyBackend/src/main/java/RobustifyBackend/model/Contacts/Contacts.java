package RobustifyBackend.model.Contacts;

import RobustifyBackend.model.Purchases.Purchase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "Contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(max = 20)
    private String name;

    private String email;

    @NotBlank
    @Size(max = 120)
    private String location;


    @NotBlank
    @Size(max = 10)
    private String phone;

    @Enumerated(EnumType.STRING)
    private EContacts type;


    public Contacts() {

    }

    public Contacts(String name, String email, String location,
                    String phone, EContacts type) {

        this.name = name;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.type = type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EContacts getType() {
        return type;
    }

    public void setType(EContacts type) {
        this.type = type;
    }
}