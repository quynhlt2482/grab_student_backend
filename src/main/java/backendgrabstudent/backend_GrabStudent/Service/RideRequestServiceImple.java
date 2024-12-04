package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestDTO;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;
import backendgrabstudent.backend_GrabStudent.Mapper.RideRequestMapper;
import backendgrabstudent.backend_GrabStudent.Repository.RideRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideRequestServiceImple implements RideRequestService{

    private RideRequestRepository repository;
    private RideRequestMapper rideRequestMapper;

    @Autowired
    public RideRequestServiceImple(RideRequestRepository repository, RideRequestMapper rideRequestMapper) {
        this.repository = repository;
        this.rideRequestMapper = rideRequestMapper;
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

    }

    @Override
    public RideRequest addRideRequest(RideRequest rideRequest) {
        return null;
    }

    @Override
    public RideRequest updateRideRequest(RideRequest rideRequest) {
        return null;
    }
}
