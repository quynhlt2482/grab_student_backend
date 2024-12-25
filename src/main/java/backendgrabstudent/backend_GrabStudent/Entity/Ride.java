package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

    @OneToMany(mappedBy = "ride")
    private List<RideReview> rideReviews;

    private String riderStartLocation;
    private String riderEndLocation;
    private BigDecimal startLat;
    private BigDecimal startLon;
    private BigDecimal endLat;
    private BigDecimal endLon;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String estimatedTime;
    private String distance;
}
