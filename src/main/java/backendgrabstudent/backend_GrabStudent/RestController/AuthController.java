package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.LoginRequest;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.LoginResponse;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RefreshTokenRequest;
import backendgrabstudent.backend_GrabStudent.Entity.RefreshToken;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Security.JwtUtil;
import backendgrabstudent.backend_GrabStudent.Service.AuthService;
import backendgrabstudent.backend_GrabStudent.Service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.getRefreshToken(request.getRefreshToken());
            if (refreshTokenService.isRefreshTokenExpired(refreshToken) || refreshToken.getRevoked()) {
                throw new CustomException(ErrorNumber.REFRESH_TOKEN_EXPIRED);
            }
            String newAccessToken = jwtUtil.generateToken(refreshToken.getStudent().getEmail(),refreshToken.getStudent().getId());
            return ResponseEntity.ok(new LoginResponse(newAccessToken, refreshToken.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
            refreshTokenService.revokeRefreshToken();
            return ResponseEntity.ok("Logged out successfully");
    }
}
