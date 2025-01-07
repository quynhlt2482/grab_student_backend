package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    Student driver;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    Student passenger;

    @OneToOne
    @JoinColumn(name = "ride_request_id", referencedColumnName = "id", unique = true)
    RideRequest rideRequest;

    @OneToMany(mappedBy = "ride")
    List<RideReview> rideReviews;

    String riderStartLocation;
    String riderEndLocation;
    @Column(precision = 38, scale = 30) // Ánh xạ thành DECIMAL(20,20)
    BigDecimal startLat;
    @Column(precision = 38, scale = 30) // Ánh xạ thành DECIMAL(20,20)
    BigDecimal startLon;
    @Column(precision = 38, scale = 30) // Ánh xạ thành DECIMAL(20,20)
    BigDecimal endLon;
    @Column(precision = 38, scale = 30) // Ánh xạ thành DECIMAL(20,20)
    BigDecimal endLat;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String status;
    String estimatedTime;
    String distance;
}
