package RobustifyBackend.Services;

import RobustifyBackend.Controllers.DTOs.UpdateOrderStatus;
import RobustifyBackend.Controllers.DTOs.UpdatePurchaseStatus;
import RobustifyBackend.Repositories.PurchaseRepository;
import RobustifyBackend.model.Order.Order;
import RobustifyBackend.model.Purchases.Purchase;
import RobustifyBackend.model.Purchases.Status;
import RobustifyBackend.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;


    @Transactional
    public Purchase updatePurchase(Long id, UpdatePurchaseStatus request) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        if (request.getStatus() != null) {
            purchase.setStatus(request.getStatus());
        }
        if (request.getQuantity() != null) {
            purchase.setQuantity(request.getQuantity());

        }

        return purchaseRepository.save(purchase);
    }

}
