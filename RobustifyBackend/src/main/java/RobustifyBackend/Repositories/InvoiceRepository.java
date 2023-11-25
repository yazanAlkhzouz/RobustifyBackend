package RobustifyBackend.Repositories;

import RobustifyBackend.model.Invoices.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoices, String > {
     boolean existsById(String id);
}
