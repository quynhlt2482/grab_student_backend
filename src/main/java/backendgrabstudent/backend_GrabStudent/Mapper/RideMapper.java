package backendgrabstudent.backend_GrabStudent.Mapper;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Ride;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RideMapper {
    @Mapping(target = "rider", source = "driver")
    @Mapping(target = "passenger", source = "passenger")
    @Mapping(target = "rideRequest", source = "rideRequest")
    RideResponseDTO toRideResponse(Ride ride);

    List<RideResponseDTO> toRideResponseList(List<Ride> rides);
}
