package RobustifyBackend.Controllers.DTOs;

import RobustifyBackend.model.Contacts.EContacts;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactDTO {
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

    public ContactDTO(String name, String email, String location, String phone, EContacts type) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setType(EContacts type) {
        this.type = type;
    }
}
