package RobustifyBackend.Controllers;

import RobustifyBackend.Controllers.DTOs.UserDTO;
import RobustifyBackend.Payload.request.SignUpRequest;
import RobustifyBackend.Payload.response.MessageResponse;
import RobustifyBackend.Repositories.OrderRepository;
import RobustifyBackend.Repositories.UserRepository;
import RobustifyBackend.SecurityConfig.jwt.JwtUtils;
import RobustifyBackend.Services.UtilizationService;
import RobustifyBackend.model.Order.Order;
import RobustifyBackend.model.User.EDepartment;
import RobustifyBackend.model.User.User;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepositories;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UtilizationService utilizationService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepositories.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        User userAuth = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getDepartment(),
                signUpRequest.getRole());

        userRepositories.save(userAuth);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userRepositories.findAll();
//
//        return ResponseEntity.ok(users);
//    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers( @RequestParam(name = "department") String departmentStr,
                                                      @RequestParam(name = "utilization", required = false) Boolean utilizationRequested) {
        EDepartment department ;
        try {
           department= EDepartment.valueOf(departmentStr);

        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body("Invalid department value");

        }
        List<User> users = userRepositories.findByDepartment(department);
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUserName());
            userDTO.setDepartment(user.getDepartment());
            userDTO.setRole(user.getRoles());

            if (utilizationRequested != null && utilizationRequested) {

            // Fetch orders assigned to the user
            List<Order> assignedOrders = orderRepository.findByAssignto(user);

            // Calculate total material consumption if needed
            double totalMaterialConsumption = 0;
            if (user.getDepartment() == EDepartment.PRINTING || user.getDepartment() == EDepartment.CUTTING_PACKAGING) {
                for (Order order : assignedOrders) {
                    totalMaterialConsumption += order.getConsumptions();
                }
            }

            // Calculate utilization
                double utilization = utilizationService.calculateUtilization(user.getDepartment(), assignedOrders.size(), totalMaterialConsumption);
                userDTO.setUtilization(utilization);
                userDTO.setNumberOfOrders(assignedOrders.size());

            }
            userDTOs.add(userDTO);
        }

        return ResponseEntity.ok(userDTOs);
    }


    @PutMapping("/users/{userId}")
    public ResponseEntity<MessageResponse> updateUser(@PathVariable Long userId,
            @Valid @RequestBody SignUpRequest updatedUserData) {
        if (!userRepositories.existsById(userId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found!"));
        }

        User user = userRepositories.findById(userId).get();
        user.setUserName(updatedUserData.getUsername());
        user.setEmail(updatedUserData.getEmail());
        user.setPassword(encoder.encode(updatedUserData.getPassword()));
        user.setDepartment(updatedUserData.getDepartment());
        user.setRole(updatedUserData.getRole());

        userRepositories.save(user);

        return ResponseEntity.ok(new MessageResponse("User data updated successfully!"));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long userId) {
        if (!userRepositories.existsById(userId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found!"));
        }

        userRepositories.deleteById(userId);

        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

}
