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
public class CustomerController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody Contacts contacts) {
        if (contactRepository.existsByEmail(contacts.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new Supplier's account
        Contacts customer = new Contacts();
        customer.setName(contacts.getName());
        customer.setEmail(contacts.getEmail());
        customer.setLocation(contacts.getLocation());
        customer.setPhone(contacts.getPhone());
        customer.setType(EContacts.valueOf("CUSTOMER"));

        contactRepository.save(customer);

        return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<MessageResponse> deleteCustomer(@PathVariable Long customerId) {
        if (!contactRepository.existsById(customerId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer not found!"));
        }

        contactRepository.deleteById(customerId);

        return ResponseEntity.ok(new MessageResponse("Customer deleted successfully!"));
    }

}
