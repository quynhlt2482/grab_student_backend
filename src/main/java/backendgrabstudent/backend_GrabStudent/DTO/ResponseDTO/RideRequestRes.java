package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestRes {
    private Integer id;
    private PassengerDTO passenger;
    private Integer postId;
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
