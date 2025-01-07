package backendgrabstudent.backend_GrabStudent.Mapper;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestRes;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideRequestResponse;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RideRequestMapper {
    RideRequestRes toRideRequestDTO(RideRequest rideRequest);

    @Mapping(source = "post.id", target = "postId")
    RideRequestResponse toRideRequestResponse(RideRequest rideRequest);

    List<RideRequestRes> toRideRequestDTOs(List<RideRequest> rideRequests);
}
