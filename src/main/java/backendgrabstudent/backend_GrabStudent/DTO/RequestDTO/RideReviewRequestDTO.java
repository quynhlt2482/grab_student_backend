package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideReviewRequestDTO {
    @NotNull(message = "rating must not be null")
    private Integer rating;
    private String comment;
    @NotNull(message = "rideId must not be null")
    private Integer rideId;
    @NotNull(message = "reviewerId must not be null")
    private Integer reviewerId;
    @NotNull(message = "reviewedId must not be null")
    private Integer reviewedId;
}
