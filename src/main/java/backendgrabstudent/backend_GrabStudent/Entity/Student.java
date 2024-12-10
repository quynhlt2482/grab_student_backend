package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String password;

    @Column(name = "phonenumber")
    private String phonenumber;
    
    @Column(name = "ride_point")
    private Integer ridePoint;

    @Column(name = "is_banned")
    private Boolean isBanned;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "is_2fa_enabled")
    private Boolean is2faEnabled;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "otp_code")
    private String otpCode;

    @Column(name = "time_otp")
    private LocalDateTime timeOtp;

    @Column(name = "verify_student")
    private Boolean verifyStudent;

    @OneToMany(mappedBy = "student")
    private List<Post> posts;

    @OneToMany(mappedBy = "driver")
    private List<Ride> rides;

    @OneToMany(mappedBy = "passenger")
    private List<RideRequest> rideRequests;

    @OneToMany(mappedBy = "reviewer")
    private List<RideReview> reviews;

    @OneToMany(mappedBy = "reviewed")
    private List<RideReview> reviewedRides;

    @OneToMany(mappedBy = "student")
    private List<ConversationMember> conversationMembers;

    @OneToMany(mappedBy = "student")
    private List<RefreshToken> refreshTokens;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public LocalDateTime getTimeOtp() {
        return timeOtp;
    }

    public void setTimeOtp(LocalDateTime timeOtp) {
        this.timeOtp = timeOtp;
    }

    public Boolean getVerifyStudent() {
        return verifyStudent;
    }

    public void setVerifyStudent(Boolean verifyStudent) {
        this.verifyStudent = verifyStudent;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public List<RideRequest> getRideRequests() {
        return rideRequests;
    }

    public void setRideRequests(List<RideRequest> rideRequests) {
        this.rideRequests = rideRequests;
    }

    public List<RideReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<RideReview> reviews) {
        this.reviews = reviews;
    }

    public List<RideReview> getReviewedRides() {
        return reviewedRides;
    }

    public void setReviewedRides(List<RideReview> reviewedRides) {
        this.reviewedRides = reviewedRides;
    }

    public List<ConversationMember> getConversationMembers() {
        return conversationMembers;
    }

    public void setConversationMembers(List<ConversationMember> conversationMembers) {
        this.conversationMembers = conversationMembers;
    }

    public List<RefreshToken> getRefreshTokens() {
        return refreshTokens;
    }

    public void setRefreshTokens(List<RefreshToken> refreshTokens) {
        this.refreshTokens = refreshTokens;
    }
}
