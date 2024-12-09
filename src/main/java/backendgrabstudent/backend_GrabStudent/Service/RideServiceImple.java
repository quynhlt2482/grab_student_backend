package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.Entity.Ride;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Repository.RideRepository;
import backendgrabstudent.backend_GrabStudent.Repository.RideRequestRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RideServiceImple implements RideService{
    private RideRepository rideRepository;
    private StudentRepository studentRepository;
    private RideRequestRepository rideRequestRepository;

    @Autowired
    public RideServiceImple(RideRepository rideRepository, StudentRepository studentRepository, RideRequestRepository rideRequestRepository) {
        this.rideRepository = rideRepository;
        this.studentRepository = studentRepository;
        this.rideRequestRepository = rideRequestRepository;
    }

    @Override
    public Ride createRide(Integer driverId, Integer passengerId, Integer rideRequestId) {
        Optional<Student> driver = studentRepository.findById(driverId);
        Optional<Student> passenger = studentRepository.findById(passengerId);
        Optional<RideRequest> rideRequest = rideRequestRepository.findById(rideRequestId);

        if (driver.isEmpty() || passenger.isEmpty() || rideRequest.isEmpty()) {
            throw new RuntimeException("Driver, Passenger, or RideRequest not found.");
        }

        // Create Ride
        Ride ride = new Ride();
        ride.setDriver(driver.get());
        ride.setPassenger(passenger.get());
        ride.setRideRequest(rideRequest.get());
        ride.setStartLocation(rideRequest.get().getPickUpLocation());
        ride.setEndLocation(rideRequest.get().getDropOffLocation());
        ride.setStartTime(LocalDateTime.now());
        ride.setEndTime(null);
        ride.setStatus("PENDING");

        return rideRepository.save(ride);
    }
}
