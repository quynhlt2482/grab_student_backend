package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePostRequest {
     @NotNull(message = "studentId must not be null")
     Integer studentId;

     @NotBlank(message = "pickUpLocation must not be blank")
     String pickUpLocation;

     @NotBlank(message = "dropOffLocation must not be blank")
     String dropOffLocation;

     @NotNull(message = "status must not be null")
     Boolean status;

     @NotBlank(message = "type must not be blank")
     String type;

     @NotNull(message = "pickUpLat must not be null")
     BigDecimal pickUpLat;  // Vĩ độ điểm đón

     @NotNull(message = "pickUpLon must not be null")
     BigDecimal pickUpLon;  // Kinh độ điểm đón

     @NotNull(message = "dropOffLat must not be null")
     BigDecimal dropOffLat; // Vĩ độ điểm trả

     @NotNull(message = "dropOffLon must not be null")
     BigDecimal dropOffLon; // Kinh độ điểm trả

     @NotNull(message = "startDate must not be null")
     LocalDate startDate;

     @NotBlank(message = "startTimeString must not be blank")
     String startTimeString;

     String content;
}
