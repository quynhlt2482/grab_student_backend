package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestRes;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestReq;
import backendgrabstudent.backend_GrabStudent.Service.RideRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseObject<List<RideRequestRes>> getRideRequestsByPostId(@PathVariable int postId, @RequestParam String status) {
        List<RideRequestRes> rideRequests = rideRequestService.getRideRequestByPostId(postId, status);
        return ResponseObject.<List<RideRequestRes>>builder()
                .data(rideRequests)
                .build();
    }

    @PostMapping("/create")
    public ResponseObject<RideRequestRes> createRideRequest(@Valid @RequestBody RideRequestReq rideRequestReponDTO) {
        RideRequestRes createdRideRequest = rideRequestService.addRideRequest(rideRequestReponDTO);
        return ResponseObject.<RideRequestRes>builder()
                .data(createdRideRequest)
                .build();
    }

    @PutMapping("/update/{id}")
    public ResponseObject<String> updateRideRequest(
            @PathVariable Integer id,
            @RequestBody RideRequestUpdateDTO rideRequestUpdateDTO) {

        rideRequestService.updateRideRequest(id, rideRequestUpdateDTO);
        return ResponseObject.<String>builder()
                .data("RideRequest updated successfully")
                .build();
    }
}
