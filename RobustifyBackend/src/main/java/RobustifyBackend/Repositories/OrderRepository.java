package RobustifyBackend.Repositories;

import RobustifyBackend.model.Order.Order;
import RobustifyBackend.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//    Integer countByAssign_toContaining(User user);
@Query("SELECT COUNT(distinct o) FROM Order o JOIN o.assign_to u WHERE u = :user")
Integer countOrdersAssignedToUser(@Param("user") User user);
    @Query("SELECT SUM(o.consumptions) FROM Order o JOIN o.assign_to u WHERE u.id = :userId")
    Double sumMaterialConsumptionByUserId(@Param("userId") Long userId);


}
