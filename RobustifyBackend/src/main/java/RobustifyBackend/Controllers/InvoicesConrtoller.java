package RobustifyBackend.Controllers;

import RobustifyBackend.Controllers.DTOs.ComplaintsDTO;
import RobustifyBackend.Controllers.DTOs.InvoiceDTO;
import RobustifyBackend.Repositories.InvoiceRepository;
import RobustifyBackend.Repositories.OrderRepository;
import RobustifyBackend.Services.InvoiceService;
import RobustifyBackend.model.Complaints.Complaints;
import RobustifyBackend.model.Inventory.Inventory;
import RobustifyBackend.model.Invoices.Invoices;
import RobustifyBackend.model.Order.Order;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class InvoicesConrtoller {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping("/invoices")
    public ResponseEntity<Invoices> createInvoice(@RequestBody InvoiceDTO request){
        try {
            Invoices invoices = invoiceService.createInvoice(request);
            return new ResponseEntity<>(invoices, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoices>> getAllInvoices() {
        List<Invoices> invoices = invoiceRepository.findAll();

        return ResponseEntity.ok(invoices);
    }
}
