package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.Entity.RefreshToken;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Repository.RefreshTokenRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import backendgrabstudent.backend_GrabStudent.Security.JwtUtil;
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
    private final JwtUtil jwtUtil;

    @Value("${refresh.token.expiry.duration}")
    private long refreshTokenExpiryDuration;

    public RefreshTokenServiceImple(RefreshTokenRepository refreshTokenRepository, StudentRepository studentRepository, HttpServletRequest request, JwtUtil jwtUtil) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.studentRepository = studentRepository;
        this.request = request;
        this.jwtUtil = jwtUtil;
    }

//    @Override
//    public RefreshToken createRefreshToken(Student student) {
//        RefreshToken refreshToken = new RefreshToken();
//        refreshToken.setToken(UUID.randomUUID().toString());
//        refreshToken.setStudent(student);
//        refreshToken.setCreatedAt(LocalDateTime.now());
//        refreshToken.setExpiryDatetime(LocalDateTime.now().plusSeconds(refreshTokenExpiryDuration));
//        refreshToken.setRevoked(false);
//        return refreshTokenRepository.save(refreshToken);
//    }

    @Override
    public RefreshToken getRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token).
                orElseThrow(()-> new CustomException(ErrorNumber.REFRESH_TOKEN_EXPIRED));
    }

    @Override
    public boolean isRefreshTokenExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiryDatetime().isBefore(LocalDateTime.now());
    }

    @Override
    public void revokeRefreshToken() {
        Integer id = jwtUtil.extractStudentIdFromRequest(request);

        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));

        List<RefreshToken> refreshTokens = refreshTokenRepository.findByStudent(student);

        for (RefreshToken refreshToken : refreshTokens) {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
        }
    }
}
