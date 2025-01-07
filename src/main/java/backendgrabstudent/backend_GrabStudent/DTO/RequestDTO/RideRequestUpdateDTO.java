package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RideRequestUpdateDTO {
     String pickUpLocation;
     String dropOffLocation;
     BigDecimal pickUpLat;
     BigDecimal pickUpLon;
     BigDecimal dropOffLat;
     BigDecimal dropOffLon;
     String status;
}
