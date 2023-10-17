package RobustifyBackend.Payload.response;

import RobustifyBackend.model.EDepartment;
import RobustifyBackend.model.User.ERole;

import java.util.List;

public class JwtResponse {
    private String token;

    private Long id;
    private String type = "Bearer";
    private String username;

    private EDepartment department;

    private ERole role;


    public JwtResponse(String accessToken, Long id, String name,EDepartment department ,ERole role) {
        this.token = accessToken;
        this.id = id;
        this.username = name;
        this.department= department;
        this.role = role;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
