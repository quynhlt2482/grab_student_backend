package backendgrabstudent.backend_GrabStudent.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ride_request")
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Student passenger;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne(mappedBy = "rideRequest")
    private Ride ride;

    private String pickUpLocation;
    private String dropOffLocation;
    private BigDecimal pickUpLat;
    private BigDecimal pickUpLon;
    private BigDecimal dropOffLat;
    private BigDecimal dropOffLon;
    private String status;
    private String estimatedTime;
    private String distance;
}
