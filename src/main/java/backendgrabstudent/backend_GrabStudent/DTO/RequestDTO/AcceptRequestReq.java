package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AcceptRequestReq {
    @NotNull(message = "requestId must not be null")
    Integer requestId;
    @NotNull(message = "riderId must not be null")
    Integer riderId;

    String riderStartLocation;
    String riderEndLocation;
    BigDecimal startLat;
    BigDecimal startLon;
    BigDecimal endLat;
    BigDecimal endLon;
    @NotBlank(message = "estimatedTime must not be blank")
    String estimatedTime;
    @NotBlank(message = "distance must not be blank")
    String distance;
}

