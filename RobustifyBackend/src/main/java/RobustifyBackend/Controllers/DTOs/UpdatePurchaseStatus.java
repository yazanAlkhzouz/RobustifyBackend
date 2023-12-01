package RobustifyBackend.Controllers.DTOs;

import RobustifyBackend.model.Purchases.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UpdatePurchaseStatus {
    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer quantity;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
