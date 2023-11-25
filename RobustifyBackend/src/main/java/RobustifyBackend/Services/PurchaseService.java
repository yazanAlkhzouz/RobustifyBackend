package RobustifyBackend.Services;

import RobustifyBackend.Repositories.PurchaseRepository;
import RobustifyBackend.model.Purchases.Purchase;
import RobustifyBackend.model.Purchases.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Transactional
    public void updateQuantity(Long id, Integer quantity) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
        purchase.setQuantity(quantity);
        purchaseRepository.save(purchase);
    }

    @Transactional
    public void updateStatus(Long id, Status status) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
        purchase.setStatus(status);
        purchaseRepository.save(purchase);
    }
}
