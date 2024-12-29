package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestRes;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideRequestReq;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;

import java.util.List;

public interface RideRequestService {
    public List<RideRequestRes> getRideRequestByPostId(int id, String status);
    public List<RideRequestRes> getRideRequestByUserId(int id, String status);
    public List<RideRequest> getAllRideRequests();
    public void deleteRideRequestByPostId(int id);
    public RideRequestRes addRideRequest(RideRequestReq request);
    public void updateRideRequest(Integer id, RideRequestUpdateDTO rideRequestUpdateDTO);
}
