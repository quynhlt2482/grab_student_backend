package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.AcceptRequestReq;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Ride;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RideService {
    Ride createRide(Integer PostId, Integer rideRequestId);

    RideResponseDTO acceptRequest(AcceptRequestReq request);

    List<RideResponseDTO> getAllRideByStatus(Integer userId, Boolean role, String status, LocalDateTime from, LocalDateTime to);

    RideResponseDTO getRideById(Integer id);

    void updateRide(Integer rideId, RideUpdateDTO rideUpdateDTO);

    void updateStatusToDone(Integer rideId);

    void updateStatusToCancel(Integer rideId);

    List<RideResponseDTO> getAllRide();

    void DeleteRide(Integer rideId);
}
