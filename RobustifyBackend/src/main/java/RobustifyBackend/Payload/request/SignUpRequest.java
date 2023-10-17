package RobustifyBackend.Payload.request;

import RobustifyBackend.model.EDepartment;
import RobustifyBackend.model.User.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private ERole role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotNull
    private EDepartment department;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ERole getRole() {
        return this.role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartment(EDepartment department) {
        this.department = department;
    }

    public EDepartment getDepartment() {
        return department;
    }



}
