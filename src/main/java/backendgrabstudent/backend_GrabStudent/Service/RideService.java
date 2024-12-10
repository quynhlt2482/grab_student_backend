package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideUpdateDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Ride;

import java.time.LocalDateTime;

public interface RideService {
    Ride createRide(Integer PostId, Integer rideRequestId);
    void updateRide(Integer rideId, RideUpdateDTO rideUpdateDTO);
    void updateStatusToDone(Integer rideId);
    void updateStatusToCancel(Integer rideId);
    void DeleteRide(Integer rideId);
}
