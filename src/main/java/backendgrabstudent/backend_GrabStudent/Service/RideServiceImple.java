package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.AcceptRequestReq;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Entity.Ride;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Enums.RideRequestStatusEnum;
import backendgrabstudent.backend_GrabStudent.Enums.RideStatusEnum;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Mapper.RideMapper;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;
import backendgrabstudent.backend_GrabStudent.Repository.RideRepository;
import backendgrabstudent.backend_GrabStudent.Repository.RideRequestRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RideServiceImple implements RideService {
    private final RideRepository rideRepository;
    private final StudentRepository studentRepository;
    private final RideRequestRepository rideRequestRepository;
    private final PostRepository postRepository;
    private final RideMapper rideMapper;

    @Autowired
    public RideServiceImple(RideRepository rideRepository,
                            StudentRepository studentRepository,
                            RideRequestRepository rideRequestRepository,
                            PostRepository postRepository, RideMapper rideMapper) {
        this.rideRepository = rideRepository;
        this.studentRepository = studentRepository;
        this.rideRequestRepository = rideRequestRepository;
        this.postRepository = postRepository;
        this.rideMapper = rideMapper;
    }

    @Override
    public List<RideResponseDTO> getAllRideByStatus(Integer userId, Boolean role,String status, LocalDateTime from, LocalDateTime to) {
        if (!RideStatusEnum.isValidStatus(status)) {
            throw new CustomException(ErrorNumber.INVALID_STATUS);
        }
        List<Ride> rides;
        if(Boolean.TRUE.equals(role)) {
           rides = rideRepository.findAllByRiderId(userId, status, from, to);
        } else {
            rides = rideRepository.findAllByPassengerId(userId, status, from, to);
        }
        return rideMapper.toRideResponseList(rides);
    }

    @Override
    public RideResponseDTO getRideById(Integer id) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_NOT_EXISTED));

        RideResponseDTO rideResponseDTO = rideMapper.toRideResponse(ride);
        rideResponseDTO.getRideRequest().setPostId(ride.getRideRequest().getPost().getId());

        return rideResponseDTO;
    }

    @Override
    public RideResponseDTO acceptRequest(AcceptRequestReq request) {
        Student rider = studentRepository.findById(request.getRiderId())
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_NOT_EXISTED));
        RideRequest rideRequest = rideRequestRepository.findById(request.getRequestId())
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_REQUEST_NOT_EXISTED));

        if (rideRepository.existsByRideRequestId(request.getRequestId()) && rideRepository.existsByDriverId(request.getRiderId())) {
            throw new CustomException(ErrorNumber.RIDE_EXISTED);
        }
        Ride ride = new Ride();
        ride.setDriver(rider);
        ride.setPassenger(rideRequest.getPassenger());
        ride.setRideRequest(rideRequest);
        ride.setStatus(RideStatusEnum.GOING.toString());
        ride.setStartTime(LocalDateTime.now());
        ride.setEndTime(null);
        ride.setDistance(request.getDistance());
        ride.setEstimatedTime(request.getEstimatedTime());
        if ((request.getRiderStartLocation().isBlank() && !request.getRiderEndLocation().isBlank()) ||
                (!request.getRiderStartLocation().isBlank() && request.getRiderEndLocation().isBlank())) {
            throw new CustomException(ErrorNumber.SOMETHING_WENT_WRONG);
        }
        if (!request.getRiderStartLocation().isBlank() && !request.getRiderEndLocation().isBlank()) {
            ride.setRiderStartLocation(request.getRiderStartLocation());
            ride.setRiderEndLocation(request.getRiderEndLocation());
            ride.setStartLon(request.getStartLon());
            ride.setStartLat(request.getStartLat());
            ride.setEndLon(request.getEndLon());
            ride.setEndLat(request.getEndLat());
        }
        rideRequest.getPost().setStatus(false);
        rideRequest.setStatus(RideRequestStatusEnum.ACCEPTED.toString());
        rideRequestRepository.save(rideRequest);

        List<RideRequest> pendingRideRequests = rideRequestRepository
                .findAllByPostId(rideRequest.getPost().getId(), RideRequestStatusEnum.PENDING.toString());
        pendingRideRequests.forEach(item -> item.setStatus(RideRequestStatusEnum.REJECTED.toString()));
        rideRequestRepository.saveAll(pendingRideRequests);

        rideRepository.save(ride);

        return rideMapper.toRideResponse(ride);
    }

    @Override
    public Ride createRide(Integer postId, Integer rideRequestId) {
        if (rideRepository.existsByRideRequestId(rideRequestId)) {
            throw new CustomException(ErrorNumber.RIDE_EXISTED);
        }
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new CustomException(ErrorNumber.POST_NOT_EXISTED));
        RideRequest rideRequest = rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_REQUEST_NOT_EXISTED));

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
        ride.setStartTime(LocalDateTime.now());
        ride.setEndTime(null);
        ride.setStatus(RideStatusEnum.GOING.toString());

        return rideRepository.save(ride);
    }

    @Override
    public void updateStatusToDone(Integer rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_NOT_EXISTED));

        ride.setStatus("Done");
        rideRepository.save(ride);
    }

    @Override
    public void updateStatusToCancel(Integer rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_NOT_EXISTED));

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
