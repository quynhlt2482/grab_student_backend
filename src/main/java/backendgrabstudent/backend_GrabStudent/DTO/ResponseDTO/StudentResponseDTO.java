package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.*;

@Data
public class StudentResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private Boolean gender;
    private String phonenumber;
    private Integer ridePoint;
    private Boolean isBanned;
    private Boolean is2faEnabled;
    private String avatarUrl;
    private Boolean verifyStudent;

    public StudentResponseDTO() {}
    public StudentResponseDTO(Integer id, String name, String email, String phonenumber, Integer ridePoint, Boolean isBanned, Boolean is2faEnabled, String avatarUrl, Boolean verifyStudent) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.ridePoint = ridePoint;
        this.isBanned = isBanned;
        this.is2faEnabled = is2faEnabled;
        this.avatarUrl = avatarUrl;
        this.verifyStudent = verifyStudent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getRidePoint() {
        return ridePoint;
    }

    public void setRidePoint(Integer ridePoint) {
        this.ridePoint = ridePoint;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public Boolean getIs2faEnabled() {
        return is2faEnabled;
    }

    public void setIs2faEnabled(Boolean is2faEnabled) {
        this.is2faEnabled = is2faEnabled;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getVerifyStudent() {
        return verifyStudent;
    }

    public void setVerifyStudent(Boolean verifyStudent) {
        this.verifyStudent = verifyStudent;
    }
}
