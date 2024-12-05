package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestReponDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Mapper.RideRequestMapper;
import backendgrabstudent.backend_GrabStudent.Mapper.StudentMapper;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;
import backendgrabstudent.backend_GrabStudent.Repository.RideRequestRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideRequestServiceImple implements RideRequestService{

    private final RideRequestRepository rideRequestRepository;
    private final RideRequestRepository repository;
    private final RideRequestMapper rideRequestMapper;
    private final StudentRepository studentRepository;
    private final PostRepository postRepository;

    @Autowired
    public RideRequestServiceImple(RideRequestRepository repository,
                                   RideRequestMapper rideRequestMapper,
                                   RideRequestRepository rideRequestRepository,
                                   StudentRepository studentRepository,
                                   PostRepository postRepository
    ) {
        this.repository = repository;
        this.rideRequestMapper = rideRequestMapper;
        this.rideRequestRepository = rideRequestRepository;
        this.studentRepository = studentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<RideRequestDTO> getRideRequestByPostId(int id) {
        List<RideRequest> rideRequests = repository.findByPostId(id);
        return rideRequestMapper.toRideRequestDTOs(rideRequests);
    }

    @Override
    public List<RideRequest> getAllRideRequests() {
        return List.of();
    }

    @Override
    public void deleteRideRequestByPostId(int id) {
        rideRequestRepository.deleteById(id);
    }

    @Override
    public RideRequestReponDTO addRideRequest(RideRequestReponDTO rideRequestReponDTO) {
        Student student = studentRepository.findById(rideRequestReponDTO.getPassenger_id()).orElseThrow(() -> new RuntimeException("Student not found"));
        Post post = postRepository.findById(rideRequestReponDTO.getPost_id()).orElseThrow(() -> new RuntimeException("Post not found"));
        if (!post.getStatus()) {
            throw new RuntimeException("Cannot add RideRequest: Post is closed");
        }

        RideRequest rideRequest = new RideRequest();
        rideRequest.setPassenger(student);
        rideRequest.setPost(post);
        rideRequest.setPickUpLocation(rideRequestReponDTO.getPickUpLocation());
        rideRequest.setDropOffLocation(rideRequestReponDTO.getDropOffLocation());
        rideRequest.setDropOffLat(rideRequestReponDTO.getDropOffLat());
        rideRequest.setPickUpLat(rideRequestReponDTO.getPickUpLat());
        rideRequest.setPickUpLon(rideRequestReponDTO.getPickUpLon());
        rideRequest.setDropOffLon(rideRequestReponDTO.getDropOffLon());
        rideRequest.setStatus(rideRequestReponDTO.getStatus());

        repository.save(rideRequest);

        return rideRequestReponDTO;
    }

    @Override
    public void updateRideRequest(Integer id, RideRequestUpdateDTO rideRequestUpdateDTO) {
        RideRequest rideRequest = rideRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RideRequest with ID " + id + " not found"));

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

        rideRequestRepository.save(rideRequest);
    }
}
