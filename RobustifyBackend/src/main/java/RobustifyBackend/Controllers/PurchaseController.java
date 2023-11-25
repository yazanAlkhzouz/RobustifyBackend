package RobustifyBackend.Controllers;

import RobustifyBackend.Controllers.DTOs.UpdatePurchaseQuantity;
import RobustifyBackend.Controllers.DTOs.UpdatePurchaseStatus;
import RobustifyBackend.Payload.response.MessageResponse;
import RobustifyBackend.Repositories.ContactRepository;
import RobustifyBackend.Repositories.InventoryRepository;
import RobustifyBackend.Repositories.PurchaseRepository;
import RobustifyBackend.Services.PurchaseService;
import RobustifyBackend.model.Contacts.Contacts;
import RobustifyBackend.model.Contacts.EContacts;
import RobustifyBackend.model.Inventory.Inventory;
import RobustifyBackend.model.Purchases.Purchase;
import RobustifyBackend.model.Purchases.Status;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PurchaseController {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/purchase")
    public ResponseEntity<?> createPurchase(@Valid @RequestBody Purchase purchaseDTO) {

        Contacts supplier = new Contacts();
        supplier.setName(purchaseDTO.getSupplier().getName());
        supplier.setEmail(purchaseDTO.getSupplier().getEmail());
        supplier.setLocation(purchaseDTO.getSupplier().getLocation());
        supplier.setPhone(purchaseDTO.getSupplier().getPhone());
        supplier.setType(EContacts.valueOf("SUPPLIER"));
        contactRepository.save(supplier);
        Purchase purchase = new Purchase();
        purchase.setSupplier(supplier);
        purchase.setItem(purchaseDTO.getItem());
        purchase.setQuantity(purchaseDTO.getQuantity());
        purchase.setPurchase_date(purchaseDTO.getPurchase_date());
        purchase.setBookInDate(purchaseDTO.getBookInDate());
        purchase.setArrivalDate(purchaseDTO.getArrivalDate());
        purchase.setStatus(Status.NOT_STARTED);

        Inventory inventoryItem = inventoryRepository.findByItem(purchaseDTO.getItem())
                .orElseThrow(() -> new RuntimeException("Item not found in inventory"));

        inventoryItem.increaseQuantity(purchaseDTO.getQuantity());
        inventoryRepository.save(inventoryItem);

        purchaseRepository.save(purchase);
        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<Purchase>> getAllPurchases() {
            List<Purchase> purchases = purchaseRepository.findAll();
            return ResponseEntity.ok(purchases);
            }

    @PutMapping("/purchaseQuantity/{id}")
    public ResponseEntity<?> updateQuantity(@PathVariable Long id, @RequestBody UpdatePurchaseQuantity request) {
        try {
            purchaseService.updateQuantity(id, request.getQuantity());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/purchaseStatus/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody UpdatePurchaseStatus request) {
        try {
            purchaseService.updateStatus(id, request.getStatus());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/purchase/{id}")
    public ResponseEntity<MessageResponse> deletePurchase(@PathVariable Long id) {
        if (!purchaseRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Purchase not found!"));
        }

        purchaseRepository.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("Purchase deleted successfully!"));
    }


}
