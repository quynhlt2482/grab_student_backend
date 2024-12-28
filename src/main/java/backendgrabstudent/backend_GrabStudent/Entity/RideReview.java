package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ride_review")
public class RideReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rating;
    private String comment;

    @ManyToOne
    private Ride ride;

    @ManyToOne
    private Student reviewer;

    @ManyToOne
    private Student reviewed;
}
