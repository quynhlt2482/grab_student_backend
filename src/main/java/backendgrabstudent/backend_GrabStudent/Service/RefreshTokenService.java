package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.Entity.RefreshToken;
import backendgrabstudent.backend_GrabStudent.Entity.Student;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Student student);
    Optional<RefreshToken> getRefreshToken(String token);
    boolean isRefreshTokenExpired(RefreshToken refreshToken);
    void revokeRefreshToken(String token);
}
