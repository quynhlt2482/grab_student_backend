package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideUpdateDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Entity.Ride;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;
import backendgrabstudent.backend_GrabStudent.Repository.RideRepository;
import backendgrabstudent.backend_GrabStudent.Repository.RideRequestRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RideServiceImple implements RideService{
    private final RideRepository rideRepository;
    private final StudentRepository studentRepository;
    private final RideRequestRepository rideRequestRepository;
    private final PostRepository postRepository;

    @Autowired
    public RideServiceImple(RideRepository rideRepository,
                            StudentRepository studentRepository,
                            RideRequestRepository rideRequestRepository,
                            PostRepository postRepository ) {
        this.rideRepository = rideRepository;
        this.studentRepository = studentRepository;
        this.rideRequestRepository = rideRequestRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Ride createRide(Integer postId,Integer rideRequestId) {
        Post post = postRepository.findById(postId).
                orElseThrow(()-> new CustomException(ErrorNumber.POST_NOT_EXISTED));
        RideRequest rideRequest = rideRequestRepository.findById(rideRequestId)
                .orElseThrow(()-> new CustomException(ErrorNumber.RIDE_REQUEST_NOT_EXISTED));

        Student driver = post.getStudent();
        Student passenger = rideRequest.getPassenger();
        if (driver == null)
            throw new CustomException(ErrorNumber.DRIVER_NOT_EXISTED);
        if (passenger == null)
            throw new CustomException(ErrorNumber.PASSENGER_NOT_EXISTED);

        Ride ride = new Ride();
        ride.setDriver(driver);
        ride.setPassenger(passenger);
        ride.setRideRequest(rideRequest);
        ride.setStartLocation(rideRequest.getPickUpLocation());
        ride.setEndLocation(rideRequest.getDropOffLocation());
        ride.setStartTime(LocalDateTime.now());
        ride.setEndTime(null);
        ride.setStatus("PENDING");

        return rideRepository.save(ride);
    }

    @Override
    public void updateStatusToDone(Integer rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(()-> new CustomException(ErrorNumber.RIDE_NOT_EXISTED));

        ride.setStatus("Done");
        rideRepository.save(ride);
    }

    @Override
    public void updateStatusToCancel(Integer rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(()-> new CustomException(ErrorNumber.RIDE_NOT_EXISTED));

        ride.setStatus("Cancel");
        rideRepository.save(ride);
    }

    @Override
    public void updateRide(Integer rideId, RideUpdateDTO rideUpdateDTO) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_NOT_EXISTED));

        ride.setRideRequest(ride.getRideRequest());
    }

    @Override
    public void DeleteRide(Integer rideId) {

    }
}
