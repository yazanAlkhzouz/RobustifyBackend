package RobustifyBackend.Repositories;

import RobustifyBackend.model.Complaints.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintsRepository extends JpaRepository<Complaints, Long> {
}
