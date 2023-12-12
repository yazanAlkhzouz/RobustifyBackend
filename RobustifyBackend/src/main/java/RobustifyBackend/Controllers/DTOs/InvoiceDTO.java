package RobustifyBackend.Controllers.DTOs;

import RobustifyBackend.model.User.EDepartment;
import RobustifyBackend.model.User.ERole;
import lombok.Data;

@Data
public class InvoiceDTO {

    private Long orderId;
    private double unitPrice;
    private double discount;

}
