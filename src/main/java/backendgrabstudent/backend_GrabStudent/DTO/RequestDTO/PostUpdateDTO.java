package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PostUpdateDTO {
    private String pickUpLocation;
    private String dropOffLocation;
    private String status;
    private BigDecimal pickUpLat;
    private BigDecimal pickUpLon;
    private BigDecimal dropOffLat;
    private BigDecimal dropOffLon;
    private String startDate;
    private String startTimeString;

}
