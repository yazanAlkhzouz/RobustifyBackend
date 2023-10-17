package RobustifyBackend.model.User;

import RobustifyBackend.model.EDepartment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name = "Users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    private String email;


    @NotBlank
    @Size(max = 120)
    private String password;


    @Enumerated(EnumType.STRING)
    @NotNull
    private EDepartment department;


    @Enumerated(EnumType.STRING)
    @NotNull
    private ERole role;

    public User(){

    }
    public User(String username, String email, String password,
                 EDepartment department,ERole role) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.department = department;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EDepartment getDepartment() {
        return department;
    }

    public void setDepartment(EDepartment department) {
        this.department = department;
    }

    public ERole getRoles() {
        return role;
    }
    public void setRoles(ERole role) {
        this.role = role;
    }
}
