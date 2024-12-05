package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.LoginRequest;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.LoginResponse;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RefreshTokenRequest;
import backendgrabstudent.backend_GrabStudent.Entity.RefreshToken;
import backendgrabstudent.backend_GrabStudent.Security.JwtUtil;
import backendgrabstudent.backend_GrabStudent.Service.AuthService;
import backendgrabstudent.backend_GrabStudent.Service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Optional<RefreshToken> optionalToken = refreshTokenService.getRefreshToken(request.getRefreshToken());

        if (optionalToken.isPresent()) {
            RefreshToken refreshToken = optionalToken.get();

            if (refreshTokenService.isRefreshTokenExpired(refreshToken) || refreshToken.getRevoked()) {
                return ResponseEntity.status(401).body("Invalid or expired refresh token");
            }

            String newAccessToken = jwtUtil.generateToken(refreshToken.getStudent().getEmail(),refreshToken.getStudent().getId());
            return ResponseEntity.ok(new LoginResponse(newAccessToken, refreshToken.getToken()));
        }

        return ResponseEntity.status(404).body("Refresh token not found");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        try {
            refreshTokenService.revokeRefreshToken();

            return ResponseEntity.ok("Logged out successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error during logout: " + e.getMessage());
        }
    }


}
