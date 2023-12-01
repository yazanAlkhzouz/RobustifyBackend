package RobustifyBackend.Services;

import RobustifyBackend.Controllers.DTOs.UpdateOrderStatus;
import RobustifyBackend.Repositories.OrderRepository;
import RobustifyBackend.Repositories.UserRepository;
import RobustifyBackend.model.Order.Order;
import RobustifyBackend.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Order updateOrder(Long id, UpdateOrderStatus request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            order.setAssign_to(user);
        }

        return orderRepository.save(order);
    }




}
