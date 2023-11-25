package RobustifyBackend.Controllers;

import RobustifyBackend.Controllers.DTOs.ComplaintsDTO;
import RobustifyBackend.Exceptions.ResourceNotFoundException;
import RobustifyBackend.Payload.response.MessageResponse;
import RobustifyBackend.Repositories.ComplaintsRepository;
import RobustifyBackend.Repositories.OrderRepository;
import RobustifyBackend.Services.ComplaintService;
import RobustifyBackend.model.Complaints.Complaints;
import RobustifyBackend.model.Order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ComplaintsController {

    @Autowired
    private ComplaintsRepository complaintsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/complaint")
    public ResponseEntity<Complaints> createComplaint(@RequestBody ComplaintsDTO request){
        try {
            Complaints complaint = complaintService.addComplaint(request);
            return new ResponseEntity<>(complaint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/complaints")
    public ResponseEntity<List<Complaints>> getAllComplaints() {
        List<Complaints> complaints = complaintsRepository.findAll();
        return ResponseEntity.ok(complaints);
    }

    @GetMapping("/complaint/{id}")
    public ResponseEntity<Complaints> getComplaints(@PathVariable Long id) {
        Optional<Complaints> complaint = complaintsRepository.findById(id);

        if (complaint.isPresent()) {
            return ResponseEntity.ok(complaint.get());
        } else {
            throw new ResourceNotFoundException("Complaint with ID " + id + " not found");
        }
    }

    @DeleteMapping("/complaint/{id}")
    public ResponseEntity<MessageResponse> deleteComplaint(@PathVariable Long id) {
        if (!complaintsRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Complaint not found!"));
        }

        complaintsRepository.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("Complaint deleted successfully!"));
    }





}
