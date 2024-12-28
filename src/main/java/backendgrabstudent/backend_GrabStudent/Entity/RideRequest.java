package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    Student passenger;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    @OneToOne(mappedBy = "rideRequest")
    Ride ride;

    String pickUpLocation;
    String dropOffLocation;
    @Column(precision = 38, scale = 30)
    BigDecimal pickUpLat;

    @Column(precision = 38, scale = 30)
    BigDecimal pickUpLon;

    @Column(precision = 38, scale = 30)
    BigDecimal dropOffLat;

    @Column(precision = 38, scale = 30)
    BigDecimal dropOffLon;
    String status;
    String estimatedTime;
    String distance;
}
