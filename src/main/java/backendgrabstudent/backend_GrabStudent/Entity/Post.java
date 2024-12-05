package backendgrabstudent.backend_GrabStudent.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    private String pickUpLocation;
    private String dropOffLocation;
    private Boolean status;
    private String type;
    private BigDecimal pickUpLat;
    private BigDecimal pickUpLon;
    private BigDecimal dropOffLat;
    private BigDecimal dropOffLon;
    private String startDate;
    private String startTimeString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
