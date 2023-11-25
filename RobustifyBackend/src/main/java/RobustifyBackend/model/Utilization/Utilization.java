package RobustifyBackend.model.Utilization;

import RobustifyBackend.model.User.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Utilization")
public class Utilization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    private double utilization;

}
