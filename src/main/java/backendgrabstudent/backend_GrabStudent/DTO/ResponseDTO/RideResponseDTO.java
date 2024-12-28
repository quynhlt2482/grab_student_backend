package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RideResponseDTO {
    Integer id;
    String riderStartLocation;
    String riderEndLocation;
    BigDecimal startLat;
    BigDecimal startLon;
    BigDecimal endLat;
    BigDecimal endLon;
    LocalDateTime startTime;
    String status;
    String estimatedTime;
    String distance;
    StudentResponseDTO rider;
    StudentResponseDTO passenger;
    RideRequestResponse rideRequest;
}
