package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import java.math.BigDecimal;

public class PostResponseDTO {
    private Integer id;
    private Integer studentId;
    private String pickUpLocation;
    private String dropOffLocation;
    private Boolean status;
    private String type;
    private BigDecimal pickUpLat;  // Vĩ độ điểm đón
    private BigDecimal pickUpLon;  // Kinh độ điểm đón
    private BigDecimal dropOffLat; // Vĩ độ điểm trả
    private BigDecimal dropOffLon; // Kinh độ điểm trả
    private String startDate;
    private String startTimeString;


    public PostResponseDTO(Integer id, Integer studentId, String pickUpLocation,
                           String dropOffLocation, Boolean status, String type,
                           BigDecimal pickUpLat, BigDecimal pickUpLon, BigDecimal dropOffLat,
                           BigDecimal dropOffLon, String startDate, String startTimeString) {
        this.id = id;
        this.studentId = studentId;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.status = status;
        this.type = type;
        this.pickUpLat = pickUpLat;
        this.pickUpLon = pickUpLon;
        this.dropOffLat = dropOffLat;
        this.dropOffLon = dropOffLon;
        this.startDate = startDate;
        this.startTimeString = startTimeString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }
}

