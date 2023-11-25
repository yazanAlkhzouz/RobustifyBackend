package RobustifyBackend.Services;

import RobustifyBackend.Repositories.OrderRepository;
import RobustifyBackend.Repositories.UtilizationRepository;
import RobustifyBackend.model.Order.Order;
import RobustifyBackend.model.User.EDepartment;
import RobustifyBackend.model.User.User;
import RobustifyBackend.model.Utilization.Utilization;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UtilizationService {
    private  UtilizationRepository utilizationRepository;
    private  OrderRepository orderRepository;

    public UtilizationService(UtilizationRepository utilizationRepository, OrderRepository orderRepository) {
        this.utilizationRepository = utilizationRepository;
        this.orderRepository = orderRepository;
    }

    public void calculateAndSetUtilizationForUser(User user) {
        Integer assignedOrdersCount = orderRepository.countOrdersAssignedToUser(user);
        double totalMaterialConsumption = orderRepository.sumMaterialConsumptionByUserId(user.getId());
        double utilization = calculateUtilization(user.getDepartment(), assignedOrdersCount, totalMaterialConsumption);
        updateUtilizationRecord(user, utilization);


    }
    private double calculateUtilization(EDepartment department, Integer assignedOrdersCount, double totalMaterialConsumption) {
        double utilization = 0.0;

        switch (department) {
            case DESIGN:
                utilization = (assignedOrdersCount * 180.0) / 480.0;
                break;
            case MONTAGE:
                utilization = (assignedOrdersCount * 20.0) / 480.0;
                break;
            case PRINTING:
                utilization = (assignedOrdersCount * 9.6 * totalMaterialConsumption ) / 480.0;
                break;
            case CUTTING_PACKAGING:
                utilization = (assignedOrdersCount * 100.0 * totalMaterialConsumption) / 480.0;
                break;
            case DELIVERY:
                utilization = (assignedOrdersCount * 40.0) / 480.0;
                break;
            default:
                throw new IllegalArgumentException("Unknown department: " + department);
        }

        return utilization;
    }

    private void updateUtilizationRecord(User user, double utilization) {
        Utilization utilizationRecord = utilizationRepository.findByUser(user)
                .orElse(new Utilization());

        utilizationRecord.setUser(user);
        utilizationRecord.setUtilization(utilization);

        utilizationRepository.save(utilizationRecord);
    }
}
