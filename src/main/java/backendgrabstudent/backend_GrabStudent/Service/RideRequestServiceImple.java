package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestRes;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestReq;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Enums.RideRequestStatusEnum;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Mapper.RideRequestMapper;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;
import backendgrabstudent.backend_GrabStudent.Repository.RideRequestRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideRequestServiceImple implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;
    private final RideRequestMapper rideRequestMapper;
    private final StudentRepository studentRepository;
    private final PostRepository postRepository;

    @Autowired
    public RideRequestServiceImple(
            RideRequestMapper rideRequestMapper,
            RideRequestRepository rideRequestRepository,
            StudentRepository studentRepository,
            PostRepository postRepository
    ) {
        this.rideRequestMapper = rideRequestMapper;
        this.rideRequestRepository = rideRequestRepository;
        this.studentRepository = studentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<RideRequestRes> getRideRequestByPostId(int id, String status) {
        if (!RideRequestStatusEnum.isValidStatus(status)) {
            throw new CustomException(ErrorNumber.INVALID_STATUS);
        }
        List<RideRequest> rideRequests = rideRequestRepository.findAllByPostId(id, status);
        return rideRequestMapper.toRideRequestDTOs(rideRequests);
    }

    @Override
    public List<RideRequestRes> getRideRequestByUserId(int id, String status) {
        if (!RideRequestStatusEnum.isValidStatus(status)) {
            throw new CustomException(ErrorNumber.INVALID_STATUS);
        }
        List<RideRequest> rideRequests = rideRequestRepository.findAllByUserId(id, status);
        return rideRequestMapper.toRideRequestDTOs(rideRequests);
    }

    @Override
    public List<RideRequestRes> getAllRideRequests() {
        List<RideRequest> rideRequests = rideRequestRepository.findAll();
        return rideRequestMapper.toRideRequestDTOs(rideRequests);
    }

    @Override
    public void deleteRideRequestByPostId(int id) {
        rideRequestRepository.deleteById(id);
    }

    @Override
    public RideRequestRes addRideRequest(RideRequestReq request) {
        if (rideRequestRepository.existsByPostIdAndPassengerId(request.getPost_id(), request.getPassenger_id())) {
            throw new CustomException(ErrorNumber.RIDE_REQUEST_EXISTED);
        }
        Student student = studentRepository.findById(request.getPassenger_id())
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        Post post = postRepository.findById(request.getPost_id())
                .orElseThrow(() -> new CustomException(ErrorNumber.POST_NOT_EXISTED));
        if (!post.getStatus()) {
            throw new CustomException(ErrorNumber.POST_IS_CLOSED);
        }
        if (!RideRequestStatusEnum.isValidStatus(request.getStatus())) {
            throw new CustomException(ErrorNumber.INVALID_STATUS);
        }

        RideRequest rideRequest = new RideRequest();
        rideRequest.setPassenger(student);
        rideRequest.setPost(post);
        rideRequest.setPickUpLocation(request.getPickUpLocation());
        rideRequest.setDropOffLocation(request.getDropOffLocation());
        rideRequest.setDropOffLat(request.getDropOffLat());
        rideRequest.setPickUpLat(request.getPickUpLat());
        rideRequest.setPickUpLon(request.getPickUpLon());
        rideRequest.setDropOffLon(request.getDropOffLon());
        rideRequest.setStatus(request.getStatus());
        rideRequest.setEstimatedTime(request.getEstimatedTime());
        rideRequest.setDistance(request.getDistance());

        RideRequest requestSaved = rideRequestRepository.save(rideRequest);

        return rideRequestMapper.toRideRequestDTO(requestSaved);
    }

    @Override
    public void updateRideRequest(Integer id, RideRequestUpdateDTO rideRequestUpdateDTO) {
        RideRequest rideRequest = rideRequestRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_REQUEST_NOT_EXISTED));

        if (rideRequestUpdateDTO.getPickUpLocation() != null) {
            rideRequest.setPickUpLocation(rideRequestUpdateDTO.getPickUpLocation());
        }
        if (rideRequestUpdateDTO.getDropOffLocation() != null) {
            rideRequest.setDropOffLocation(rideRequestUpdateDTO.getDropOffLocation());
        }
        if (rideRequestUpdateDTO.getPickUpLat() != null) {
            rideRequest.setPickUpLat(rideRequestUpdateDTO.getPickUpLat());
        }
        if (rideRequestUpdateDTO.getPickUpLon() != null) {
            rideRequest.setPickUpLon(rideRequestUpdateDTO.getPickUpLon());
        }
        if (rideRequestUpdateDTO.getDropOffLat() != null) {
            rideRequest.setDropOffLat(rideRequestUpdateDTO.getDropOffLat());
        }
        if (rideRequestUpdateDTO.getDropOffLon() != null) {
            rideRequest.setDropOffLon(rideRequestUpdateDTO.getDropOffLon());
        }
        if (rideRequestUpdateDTO.getStatus() != null) {
            if(!RideRequestStatusEnum.isValidStatus(rideRequestUpdateDTO.getStatus())) {
                throw new CustomException(ErrorNumber.INVALID_STATUS);
            }
            rideRequest.setStatus(rideRequestUpdateDTO.getStatus());
        }

        rideRequestRepository.save(rideRequest);
    }
}
