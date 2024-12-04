package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ride")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Student driver;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Student passenger;

    @OneToOne
    @JoinColumn(name = "ride_request_id", referencedColumnName = "id", unique = true)
    private RideRequest rideRequest;

    private String startLocation;
    private String endLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    @OneToMany(mappedBy = "ride")
    private List<RideReview> rideReviews;


}
