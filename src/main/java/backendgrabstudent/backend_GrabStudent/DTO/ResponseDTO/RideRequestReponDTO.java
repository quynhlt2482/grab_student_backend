package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;


import java.math.BigDecimal;

public class RideRequestReponDTO {

    private Integer passenger_id;
    private Integer post_id;
    private String pickUpLocation;
    private String dropOffLocation;
    private BigDecimal pickUpLat;
    private BigDecimal pickUpLon;
    private BigDecimal dropOffLat;
    private BigDecimal dropOffLon;
    private String status;

    public RideRequestReponDTO() {}

    public RideRequestReponDTO(Integer passenger_id, Integer post_id, String pickUpLocation, String dropOffLocation, BigDecimal pickUpLat, BigDecimal pickUpLon, BigDecimal dropOffLat, BigDecimal dropOffLon, String status) {
        this.passenger_id = passenger_id;
        this.post_id = post_id;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.pickUpLat = pickUpLat;
        this.pickUpLon = pickUpLon;
        this.dropOffLat = dropOffLat;
        this.dropOffLon = dropOffLon;
        this.status = status;
    }

    public Integer getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(Integer passenger_id) {
        this.passenger_id = passenger_id;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
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
