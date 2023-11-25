package RobustifyBackend.Services;

import RobustifyBackend.Controllers.DTOs.ComplaintsDTO;
import RobustifyBackend.Repositories.ComplaintsRepository;
import RobustifyBackend.Repositories.OrderRepository;
import RobustifyBackend.model.Complaints.Complaints;
import RobustifyBackend.model.Order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintsRepository complaintRepository;

    @Autowired
    private OrderRepository orderRepository;




    public Complaints addComplaint(ComplaintsDTO complaintDTO) throws Exception {
        Order order = orderRepository.findById(complaintDTO.getOrderId())
                .orElseThrow(() -> new Exception("Order not found"));


        Complaints complaint = new Complaints();
        complaint.setOrder(order);
        complaint.setDefect(complaintDTO.getDefect());
        complaint.setComment(complaintDTO.getComment());

        return complaintRepository.save(complaint);
    }
}
