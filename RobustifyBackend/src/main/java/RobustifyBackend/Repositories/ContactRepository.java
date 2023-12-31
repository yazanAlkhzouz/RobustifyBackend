package RobustifyBackend.Repositories;

import RobustifyBackend.model.Contacts.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Long> {
    Boolean existsByEmail(String email);

}
