package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.LoginRequest;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.LoginResponse;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Mapper.StudentMapper;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import backendgrabstudent.backend_GrabStudent.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    private final StudentRepository studentRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final StudentMapper studentMapper;

    @Autowired
    public AuthService(StudentRepository studentRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, RefreshTokenService refreshTokenService, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
        this.studentMapper = studentMapper;
    }

    public LoginResponse login(LoginRequest request) {
        // Tìm sinh viên theo email
        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found!"));

//        boolean isPasswordValid = passwordEncoder.matches(request.getPassword(), student.getPassword());
//        if (!isPasswordValid) {
//            throw new RuntimeException("Invalid password!");
//        }
        boolean isPasswordValid = Objects.equals(request.getPassword(), student.getPassword());
        if (!isPasswordValid) {
            throw new RuntimeException("Invalid password!");
        }
        // Tạo access token
        String accessToken = jwtUtil.generateToken(student.getEmail(), student.getId());

        // Refresh token logic
        String refreshToken = refreshTokenService.createRefreshToken(student).getToken();

        // Tạo response
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .studentInfo(studentMapper.studentToStudentResponseDTO(student))
                .build();
    }
}

