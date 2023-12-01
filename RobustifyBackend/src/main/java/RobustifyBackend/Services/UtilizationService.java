package RobustifyBackend.Services;


import RobustifyBackend.model.User.EDepartment;
import org.springframework.stereotype.Service;


@Service
public class UtilizationService {

    public double calculateUtilization(EDepartment department, Integer assignedOrdersCount, double totalMaterialConsumption) {
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

}
