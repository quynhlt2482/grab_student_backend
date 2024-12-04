package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import java.math.BigDecimal;

public class RideRequestUpdateDTO {
    private String pickUpLocation;
    private String dropOffLocation;
    private BigDecimal pickUpLat;
    private BigDecimal pickUpLon;
    private BigDecimal dropOffLat;
    private BigDecimal dropOffLon;

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
}
