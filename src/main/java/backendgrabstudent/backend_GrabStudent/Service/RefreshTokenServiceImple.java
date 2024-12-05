package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.Entity.RefreshToken;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Repository.RefreshTokenRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImple implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final StudentRepository studentRepository;
    private final HttpServletRequest request;


    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${refresh.token.expiry.duration}")
    private long refreshTokenExpiryDuration;

    public RefreshTokenServiceImple(RefreshTokenRepository refreshTokenRepository, StudentRepository studentRepository, HttpServletRequest request) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.studentRepository = studentRepository;
        this.request = request;
    }

    @Override
    public RefreshToken createRefreshToken(Student student) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setStudent(student);
        refreshToken.setCreatedAt(LocalDateTime.now());
        refreshToken.setExpiryDatetime(LocalDateTime.now().plusSeconds(refreshTokenExpiryDuration));
        refreshToken.setRevoked(false);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> getRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public boolean isRefreshTokenExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiryDatetime().isBefore(LocalDateTime.now());
    }

    @Override
    public void revokeRefreshToken() {
        String authorizationHeader = request.getHeader("Authorization");
        Integer studentId = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                // Giải mã JWT và lấy claims từ token
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();

                // Lấy student_id từ claims
                studentId = Integer.parseInt(claims.get("student_id").toString());

                if (studentId == null) {
                    throw new RuntimeException("Student ID is missing in the JWT token");
                }
            } catch (JwtException | IllegalArgumentException e) {
                // Nếu có lỗi khi giải mã JWT (token không hợp lệ, hết hạn, hoặc thiếu trường)
                throw new RuntimeException("Invalid or expired JWT token", e);
            }
        } else {
            throw new RuntimeException("Authorization header is missing or invalid");
        }

        Student student = studentRepository.findById(studentId).orElse(null);

        List<RefreshToken> refreshTokens = refreshTokenRepository.findByStudent(student);

        for (RefreshToken refreshToken : refreshTokens) {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
        }
    }
}
