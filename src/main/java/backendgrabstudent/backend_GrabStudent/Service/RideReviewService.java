package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideReviewRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideReviewResponseDTO;

import java.util.List;

public interface RideReviewService {
    RideReviewResponseDTO createRideReview(RideReviewRequestDTO requestDTO);

    RideReviewResponseDTO getRideReviewById(Integer id);

    List<RideReviewResponseDTO> getAllRideReviews();

    RideReviewResponseDTO updateRideReview(Integer id, RideReviewRequestDTO requestDTO);

    void deleteRideReview(Integer id);
}
