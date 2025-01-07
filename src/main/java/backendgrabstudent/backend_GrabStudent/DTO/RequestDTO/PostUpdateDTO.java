package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostUpdateDTO {
    String pickUpLocation;
    String dropOffLocation;
    String status;
    BigDecimal pickUpLat;
    BigDecimal pickUpLon;
    BigDecimal dropOffLat;
    BigDecimal dropOffLon;
    String startDate;
    String startTimeString;
    String content;
    String type;
}
