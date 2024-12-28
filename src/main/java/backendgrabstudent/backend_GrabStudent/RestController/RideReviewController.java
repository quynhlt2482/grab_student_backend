package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideReviewRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideReviewResponseDTO;
import backendgrabstudent.backend_GrabStudent.Service.RideReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ride-review")
@RequiredArgsConstructor
public class RideReviewController {

    private final RideReviewService rideReviewService;

    @PostMapping
    public ResponseObject<RideReviewResponseDTO> createRideReview(@RequestBody RideReviewRequestDTO requestDTO) {
        RideReviewResponseDTO createdReview = rideReviewService.createRideReview(requestDTO);
        return ResponseObject.<RideReviewResponseDTO>builder()
                .data(createdReview)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseObject<RideReviewResponseDTO> getRideReviewById(@PathVariable Integer id) {
        RideReviewResponseDTO review = rideReviewService.getRideReviewById(id);
        return ResponseObject.<RideReviewResponseDTO>builder()
                .data(review)
                .build();
    }

    @GetMapping
    public ResponseObject<List<RideReviewResponseDTO>> getAllRideReviews() {
        List<RideReviewResponseDTO> reviews = rideReviewService.getAllRideReviews();
        return ResponseObject.<List<RideReviewResponseDTO>>builder()
                .data(reviews)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseObject<RideReviewResponseDTO> updateRideReview(@PathVariable Integer id, @RequestBody RideReviewRequestDTO requestDTO) {
        RideReviewResponseDTO updatedReview = rideReviewService.updateRideReview(id, requestDTO);
        return ResponseObject.<RideReviewResponseDTO>builder()
                .data(updatedReview)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseObject<String> deleteRideReview(@PathVariable Integer id) {
        rideReviewService.deleteRideReview(id);
        return ResponseObject.<String>builder()
                .data("RideReview deleted successfully.")
                .build();
    }
}
