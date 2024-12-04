package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestDTO;
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
    private RideRequestRepository repository;
    private RideRequestMapper rideRequestMapper;
    private StudentRepository studentRepository;
    private PostRepository postRepository;

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
    public RideRequest updateRideRequest(RideRequest rideRequest) {
        return null;
    }
}
