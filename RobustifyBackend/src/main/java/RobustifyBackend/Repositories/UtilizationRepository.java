package RobustifyBackend.Repositories;

import RobustifyBackend.model.User.User;
import RobustifyBackend.model.Utilization.Utilization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilizationRepository extends JpaRepository<Utilization, Long> {
    Optional<Utilization> findByUser(User user);
}
