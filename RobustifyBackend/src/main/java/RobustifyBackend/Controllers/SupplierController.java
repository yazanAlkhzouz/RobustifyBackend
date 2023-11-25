package RobustifyBackend.Controllers;

import RobustifyBackend.Payload.response.MessageResponse;
import RobustifyBackend.Repositories.ContactRepository;
import RobustifyBackend.model.Contacts.Contacts;
import RobustifyBackend.model.Contacts.EContacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SupplierController {
    @Autowired
    private ContactRepository contactRepository;


    @PostMapping("/supplier")
    public ResponseEntity<?> createSupplier(@RequestBody Contacts contacts) {
        if (contactRepository.existsByEmail(contacts.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new Supplier's account
        Contacts supplier = new Contacts();
        supplier.setName(contacts.getName());
        supplier.setEmail(contacts.getEmail());
        supplier.setLocation(contacts.getLocation());
        supplier.setPhone(contacts.getPhone());
        supplier.setType(EContacts.valueOf("SUPPLIER"));

        contactRepository.save(supplier);

        return ResponseEntity.ok(new MessageResponse("Supplier registered successfully!"));
    }

    @DeleteMapping("/supplier/{supplierId}")
    public ResponseEntity<MessageResponse> deleteSupplier(@PathVariable Long supplierId) {
        if (!contactRepository.existsById(supplierId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Supplier not found!"));
        }

        contactRepository.deleteById(supplierId);

        return ResponseEntity.ok(new MessageResponse("Supplier deleted successfully!"));
    }
}
