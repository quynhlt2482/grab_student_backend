package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.AcceptRequestReq;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestRes;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Ride;
import backendgrabstudent.backend_GrabStudent.Service.RideService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    private RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping
    public ResponseObject<List<RideResponseDTO>> getAllRideByUser(
            @RequestParam String status,
            @RequestParam LocalDateTime from,
            @RequestParam LocalDateTime to,
            @RequestParam Integer userId,
            @RequestParam Boolean role
    ) {
        List<RideResponseDTO> rides = rideService.getAllRideByStatus(userId, role, status, from, to);

        return ResponseObject.<List<RideResponseDTO>>builder()
                .data(rides)
                .build();
    }

    @PostMapping("/accept")
    public ResponseObject<RideResponseDTO> acceptRide(@Valid @RequestBody AcceptRequestReq request) {
        var result = rideService.acceptRequest(request);

        return ResponseObject.<RideResponseDTO>builder()
                .data(result)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseObject<RideResponseDTO> getById(@PathVariable Integer id) {
        var result = rideService.getRideById(id);

        return ResponseObject.<RideResponseDTO>builder()
                .data(result)
                .build();
    }

    @GetMapping("/")
    public ResponseObject<List<RideResponseDTO>> getRideRequest() {
        List<RideResponseDTO> rides = rideService.getAllRide();
        return ResponseObject.<List<RideResponseDTO>>builder()
                .data(rides)
                .build();
    }

    @PutMapping("/update/rideDone")
    public ResponseObject<String> updateRideDone(@RequestParam Integer rideId) {
        rideService.updateStatusToDone(rideId);
        return ResponseObject.<String>builder()
                .data("Ride updated to done successfully")
                .build();
    }

    @PutMapping("/update/rideCancel")
    public ResponseObject<String> updateRideCancel(@RequestParam Integer rideId) {
        rideService.updateStatusToCancel(rideId);
        return ResponseObject.<String>builder()
                .data("Ride updated to cancel successfully")
                .build();
    }

//    @PostMapping("/create")
//    public ResponseObject<Ride> createRide(@RequestParam Integer postId, @RequestParam Integer rideRequestId) {
//        Ride createdRide = rideService.createRide(postId, rideRequestId);
//        return ResponseObject.<Ride>builder()
//                .data(createdRide)
//                .build();
//    }
}
