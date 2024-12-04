package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PassengerDTO;

import java.math.BigDecimal;

public class RideRequestDTO {
    private Integer id;
    private PassengerDTO passenger;
    private Integer postId;
    private String pickUpLocation;
    private String dropOffLocation;
    private BigDecimal pickUpLat;
    private BigDecimal pickUpLon;
    private BigDecimal dropOffLat;
    private BigDecimal dropOffLon;
    private String status;

    public RideRequestDTO() {}
    public RideRequestDTO(Integer id, PassengerDTO passenger, Integer postId, String pickUpLocation, String dropOffLocation, BigDecimal pickUpLat, BigDecimal pickUpLon, BigDecimal dropOffLat, BigDecimal dropOffLon, String status) {
        this.id = id;
        this.passenger = passenger;
        this.postId = postId;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.pickUpLat = pickUpLat;
        this.pickUpLon = pickUpLon;
        this.dropOffLat = dropOffLat;
        this.dropOffLon = dropOffLon;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PassengerDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerDTO passenger) {
        this.passenger = passenger;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public BigDecimal getPickUpLat() {
        return pickUpLat;
    }

    public void setPickUpLat(BigDecimal pickUpLat) {
        this.pickUpLat = pickUpLat;
    }

    public BigDecimal getPickUpLon() {
        return pickUpLon;
    }

    public void setPickUpLon(BigDecimal pickUpLon) {
        this.pickUpLon = pickUpLon;
    }

    public BigDecimal getDropOffLat() {
        return dropOffLat;
    }

    public void setDropOffLat(BigDecimal dropOffLat) {
        this.dropOffLat = dropOffLat;
    }

    public BigDecimal getDropOffLon() {
        return dropOffLon;
    }

    public void setDropOffLon(BigDecimal dropOffLon) {
        this.dropOffLon = dropOffLon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
