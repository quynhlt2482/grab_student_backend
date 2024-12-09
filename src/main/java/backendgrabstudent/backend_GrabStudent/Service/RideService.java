package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.Entity.Ride;

import java.time.LocalDateTime;

public interface RideService {
    public Ride createRide(Integer driverId, Integer passengerId, Integer rideRequestId);
}
