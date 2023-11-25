package RobustifyBackend.Services;

import RobustifyBackend.Repositories.OrderRepository;
import RobustifyBackend.Repositories.UserRepository;
import RobustifyBackend.model.Order.Order;
import RobustifyBackend.model.Purchases.Purchase;
import RobustifyBackend.model.Purchases.Status;
import RobustifyBackend.model.User.User;
import org.aspectj.weaver.ast.Or;
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
    public void updateStatus(Long id, Status status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Transactional
    public Order assignUser(Long orderId, Long userId){
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order Not Found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setAssign_to(user);
       return orderRepository.save(order);

    }


}
