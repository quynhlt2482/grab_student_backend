package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Mapper.StudentMapper;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImple implements StudentService {
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private EmailService emailService;
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private HttpServletRequest request;

    @Autowired
    public StudentServiceImple(StudentRepository studentRepository, StudentMapper studentMapper, EmailService emailService, HttpServletRequest request) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.emailService = emailService;
        this.request = request;
    }

    @Override
    public List<StudentResponseDTO> getAllStudent() {
        return studentRepository.findAll().stream()
                .map(studentMapper::studentToStudentResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentResponseDTO> getStudentById(int id) {
        return studentRepository.findById(id)
                .map(studentMapper::studentToStudentResponseDTO);
    }

    @Override
    public StudentResponseDTO saveStudent(StudentResponseDTO studentResponseDTO) {

        Student student = studentMapper.studentResponseDTOToStudent(studentResponseDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.studentToStudentResponseDTO(savedStudent);
    }

    @Override
    public StudentResponseDTO updateStudent(StudentResponseDTO studentResponseDTO) {
        // Chuyển đổi DTO thành Entity
        Student student = studentMapper.studentResponseDTOToStudent(studentResponseDTO);

        // Giữ lại password cũ nếu DTO không chứa password
        if (student.getPassword() == null) {
            student.setPassword(studentRepository.findById(student.getId())
                    .orElseThrow(() -> new RuntimeException("Student not found"))
                    .getPassword());
        }

        // Lưu và trả về DTO
        Student savedStudent = studentRepository.save(student);
        return studentMapper.studentToStudentResponseDTO(savedStudent);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
    // Phương thức đăng ký tài khoản và gửi OTP
    @Override
    public String registerStudent(String email) {
        Optional<Student> existingStudent = studentRepository.findByEmail(email);
        if (existingStudent.isPresent()) {
            return "Email already registered!";
        }

        // Gửi OTP và tạo bản ghi sinh viên mới
        String otp = emailService.generateOTP();
        emailService.sendOTPEmail(email, otp);

        // Tạo bản ghi mới trong cơ sở dữ liệu
        Student newStudent = new Student();
        newStudent.setEmail(email);
        newStudent.setOtpCode(otp);
        newStudent.setTimeOtp(LocalDateTime.now());
        newStudent.setVerifyStudent(false);
        newStudent.setName("");
        newStudent.setPassword("");
        newStudent.setPhonenumber("");
        studentRepository.save(newStudent);

        return "OTP has been sent to " + email;
    }

    // Phương thức xác thực OTP
    @Override
    public String verifyOtp(String email, String otp) {
        Optional<Student> studentOpt = studentRepository.findByEmail(email);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            if (student.getOtpCode().equals(otp) && student.getTimeOtp().isAfter(LocalDateTime.now().minusMinutes(5))) {
                // Nếu OTP chính xác và không hết hạn
                student.setVerifyStudent(true);
                studentRepository.save(student);
                return "OTP verified successfully!";
            } else {
                return "Invalid OTP or OTP has expired!";
            }
        } else {
            return "Email not found!";
        }
    }

    @Override
    public Optional<StudentResponseDTO> getStudentLoginInfor() {
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
        return studentRepository.findById(studentId)
                .map(studentMapper::studentToStudentResponseDTO);
    }

    @Override
    public boolean existsById(int id) {
        return studentRepository.existsById(id);
    }

    @Override
    public void updatePassword(int id, String newPassword) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with ID " + id + " not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(newPassword);

        student.setPassword(hashedPassword);
        studentRepository.save(student);
    }
}
