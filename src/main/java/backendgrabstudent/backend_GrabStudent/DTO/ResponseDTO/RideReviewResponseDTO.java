package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideReviewResponseDTO {
    private Integer id;
    private Integer rating;
    private String comment;
    private Integer rideId;
    private Integer reviewerId;
    private Integer reviewedId;
}
