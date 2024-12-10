package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.Entity.RefreshToken;
import backendgrabstudent.backend_GrabStudent.Entity.Student;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Student student);
    RefreshToken getRefreshToken(String token);
    boolean isRefreshTokenExpired(RefreshToken refreshToken);
    void revokeRefreshToken();
}
