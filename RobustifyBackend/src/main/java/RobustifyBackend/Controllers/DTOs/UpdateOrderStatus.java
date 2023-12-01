package RobustifyBackend.Controllers.DTOs;

import RobustifyBackend.model.Purchases.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UpdateOrderStatus {
    @Enumerated(EnumType.STRING)
    private Status status;

    private Long userId;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }
}
