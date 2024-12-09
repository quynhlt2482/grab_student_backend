package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RideRequestUpdateDTO {
    private String pickUpLocation;
    private String dropOffLocation;
    private BigDecimal pickUpLat;
    private BigDecimal pickUpLon;
    private BigDecimal dropOffLat;
    private BigDecimal dropOffLon;


}
