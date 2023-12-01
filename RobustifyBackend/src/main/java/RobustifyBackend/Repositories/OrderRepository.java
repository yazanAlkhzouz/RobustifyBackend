package RobustifyBackend.Repositories;

import RobustifyBackend.model.Order.Order;
import RobustifyBackend.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

        List<Order> findByAssignto(User user);

}
