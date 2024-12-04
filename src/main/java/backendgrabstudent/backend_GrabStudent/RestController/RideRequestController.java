package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestReponDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
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
    public ResponseEntity<RideRequestReponDTO> createRideRequest(@RequestBody RideRequestReponDTO rideRequestReponDTO) {
        try {
            RideRequestReponDTO createdRideRequest = rideRequestService.addRideRequest(rideRequestReponDTO);
            return ResponseEntity.ok(createdRideRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
