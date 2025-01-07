package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestReq {
    @NotNull(message = "passenger_id must not be null")
    private Integer passenger_id;

    @NotNull(message = "post_id must not be null")
    private Integer post_id;

    @NotBlank(message = "pickUpLocation must not be blank")
    private String pickUpLocation;

    @NotBlank(message = "dropOffLocation must not be blank")
    private String dropOffLocation;

    @NotNull(message = "pickUpLat must not be null")
    private BigDecimal pickUpLat;

    @NotNull(message = "pickUpLon must not be null")
    private BigDecimal pickUpLon;

    @NotNull(message = "dropOffLat must not be null")
    private BigDecimal dropOffLat;

    @NotNull(message = "dropOffLon must not be null")
    private BigDecimal dropOffLon;

    @NotBlank(message = "status must not be blank")
    private String status;

    @NotBlank(message = "estimatedTime must not be blank")
    private String estimatedTime;

    @NotBlank(message = "distance must not be blank")
    private String distance;
}
