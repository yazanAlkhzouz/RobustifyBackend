package RobustifyBackend.Controllers.DTOs;

import RobustifyBackend.model.User.EDepartment;
import RobustifyBackend.model.User.ERole;
import lombok.Data;

@Data
public class UserDTO {
        private String username;
        private EDepartment department;
        private ERole role;
        private int numberOfOrders;
        private double utilization;

}
