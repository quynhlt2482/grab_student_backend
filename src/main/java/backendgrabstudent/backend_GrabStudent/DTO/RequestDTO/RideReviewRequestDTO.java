package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideReviewRequestDTO {
    private Integer rating;
    private String comment;
    private Integer rideId;
    private Integer reviewerId;
    private Integer reviewedId;
}
