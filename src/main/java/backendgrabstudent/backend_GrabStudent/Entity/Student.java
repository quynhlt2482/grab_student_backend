package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    private String studentClass;
    private Float rating;

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

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private RefreshToken refreshTokens;

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }
}
