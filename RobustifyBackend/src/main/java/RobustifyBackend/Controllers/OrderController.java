package RobustifyBackend.Controllers;

import RobustifyBackend.Controllers.DTOs.UpdateOrderStatus;
import RobustifyBackend.Controllers.DTOs.UpdatePurchaseStatus;
import RobustifyBackend.Exceptions.ResourceNotFoundException;
import RobustifyBackend.Payload.response.MessageResponse;
import RobustifyBackend.Repositories.ContactRepository;
import RobustifyBackend.Repositories.InventoryRepository;
import RobustifyBackend.Repositories.OrderRepository;
import RobustifyBackend.Services.OrderService;
import RobustifyBackend.model.Contacts.Contacts;
import RobustifyBackend.model.Contacts.EContacts;
import RobustifyBackend.model.Inventory.Inventory;
import RobustifyBackend.model.Order.Order;
import RobustifyBackend.model.Purchases.Purchase;
import RobustifyBackend.model.Purchases.Status;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@Valid @RequestBody Order orderDTO) {

        Contacts customer = new Contacts();
        customer.setName(orderDTO.getCustomer().getName());
        customer.setEmail(orderDTO.getCustomer().getEmail());
        customer.setLocation(orderDTO.getCustomer().getLocation());
        customer.setPhone(orderDTO.getCustomer().getPhone());
        customer.setType(EContacts.valueOf("CUSTOMER"));
        contactRepository.save(customer);
        Order order = new Order();
        order.setCustomer(customer);
        order.setItem(orderDTO.getItem());
        order.setQuantity(orderDTO.getQuantity());
        order.setWidth(orderDTO.getWidth());
        order.setHeight(orderDTO.getHeight());
        order.setCost(orderDTO.getCost());
        order.setConsumptions(orderDTO.getConsumptions());
        order.setDeadline(orderDTO.getDeadline());

        // Set the status to NOT_STARTED
        order.setStatus(Status.NOT_STARTED);

        Inventory inventoryItem = inventoryRepository.findByItem(orderDTO.getItem())
                .orElseThrow(() -> new RuntimeException("Item not found in inventory"));

        inventoryItem.reduceQuantity(orderDTO.getQuantity());
        inventoryRepository.save(inventoryItem);

        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            throw new ResourceNotFoundException("Order with ID " + id + " not found");
        }
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody UpdateOrderStatus request) {
        try {
            orderService.updateStatus(id, request.getStatus());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/order/{orderId}/assignUser/{userId}")
    public ResponseEntity<Order> assignUserToOrder(@PathVariable Long orderId, @PathVariable Long userId) {
        try {
            Order updatedOrder = orderService.assignUser(orderId, userId);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/order/{id}")
    public ResponseEntity<MessageResponse> deleteorder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Order not found!"));
        }

        orderRepository.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("order deleted successfully!"));
    }



}
