package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Ride;
import backendgrabstudent.backend_GrabStudent.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
public class RideController {
    private RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/create")
    public ResponseEntity<Ride> createRide(@RequestParam Integer postId, @RequestParam Integer rideRequestId) {
        Ride createdRide = rideService.createRide(postId,rideRequestId);
        return ResponseEntity.ok(createdRide);
    }

    @PutMapping("/update/rideDone")
    public ResponseEntity<?> updateRideDone(@RequestParam Integer rideId) {
        rideService.updateStatusToDone(rideId);
        return ResponseEntity.ok("Ride updated done");
    }

    @PutMapping("/update/rideCancel")
    public ResponseEntity<?> updateRideCancel(@RequestParam Integer rideId) {
        rideService.updateStatusToCancel(rideId);
        return ResponseEntity.ok("Ride updated cancel");
    }
}
