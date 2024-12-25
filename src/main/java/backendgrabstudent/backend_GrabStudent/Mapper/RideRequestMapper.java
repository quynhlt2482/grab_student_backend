package backendgrabstudent.backend_GrabStudent.Mapper;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestRes;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RideRequestMapper {
    @Mapping(source = "rideRequest.passenger.id", target = "passenger.id")
    @Mapping(source = "rideRequest.passenger.name", target = "passenger.name")
    @Mapping(source = "rideRequest.post.id", target = "postId")
    RideRequestRes toRideRequestDTO(RideRequest rideRequest);

    List<RideRequestRes> toRideRequestDTOs(List<RideRequest> rideRequests);
}
