package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RideRequestResponse {
     Integer id;
     Integer postId;
     String pickUpLocation;
     String dropOffLocation;
     BigDecimal pickUpLat;
     BigDecimal pickUpLon;
     BigDecimal dropOffLat;
     BigDecimal dropOffLon;
     String status;
}
