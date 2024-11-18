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

    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;
    private Integer rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Student reviewer;

    @ManyToOne
    @JoinColumn(name = "is_reviewed_id")
    private Student reviewed;
}
