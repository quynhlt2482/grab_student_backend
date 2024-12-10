package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {
    private Integer id;
    private Integer studentId;
    private String pickUpLocation;
    private String dropOffLocation;
    private Boolean status;
    private String type;
    private BigDecimal pickUpLat;  // Vĩ độ điểm đón
    private BigDecimal pickUpLon;  // Kinh độ điểm đón
    private BigDecimal dropOffLat; // Vĩ độ điểm trả
    private BigDecimal dropOffLon; // Kinh độ điểm trả
    private String startDate;
    private String startTimeString;
}

