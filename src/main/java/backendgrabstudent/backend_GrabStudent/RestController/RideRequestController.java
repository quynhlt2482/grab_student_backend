package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestReponDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorResponse;
import backendgrabstudent.backend_GrabStudent.Service.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riderequest")
public class RideRequestController {
    private final RideRequestService rideRequestService;

    @Autowired
    public RideRequestController(RideRequestService rideRequestService) {
        this.rideRequestService = rideRequestService;
    }

    @GetMapping("/fineByPost/{postId}")
    public ResponseEntity<List<RideRequestDTO>> getRideRequestsByPostId(@PathVariable int postId) {
        List<RideRequestDTO> rideRequests = rideRequestService.getRideRequestByPostId(postId);

        if (rideRequests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(rideRequests);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRideRequest(@RequestBody RideRequestReponDTO rideRequestReponDTO) {
        try {
            RideRequestReponDTO createdRideRequest = rideRequestService.addRideRequest(rideRequestReponDTO);
            return ResponseEntity.ok(createdRideRequest);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "Cannot add RideRequest: " + e.getMessage(),
                    "ERR_CLOSED_POST" // Ví dụ mã lỗi
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRideRequest(
            @PathVariable Integer id,
            @RequestBody RideRequestUpdateDTO rideRequestUpdateDTO) {

        rideRequestService.updateRideRequest(id, rideRequestUpdateDTO);
        return ResponseEntity.ok("RideRequest updated successfully");
    }

}
