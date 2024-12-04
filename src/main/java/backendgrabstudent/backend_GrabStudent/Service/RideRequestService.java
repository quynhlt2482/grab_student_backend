package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestReponDTO;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;

import java.util.List;

public interface RideRequestService {
    public List<RideRequestDTO> getRideRequestByPostId(int id);
    public List<RideRequest> getAllRideRequests();
    public void deleteRideRequestByPostId(int id);
    public RideRequestReponDTO addRideRequest(RideRequestReponDTO rideRequestReponDTO);
    public void updateRideRequest(Integer id, RideRequestUpdateDTO rideRequestUpdateDTO);
}
