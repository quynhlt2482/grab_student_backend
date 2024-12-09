package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Mapper.StudentMapper;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import backendgrabstudent.backend_GrabStudent.Security.JwtUtil;
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
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final EmailService emailService;
    private final HttpServletRequest request;
    private final JwtUtil jwtUtil;

    @Autowired
    public StudentServiceImple(StudentRepository studentRepository, StudentMapper studentMapper, EmailService emailService, HttpServletRequest request, JwtUtil jwtUtil ) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.emailService = emailService;
        this.request = request;
        this.jwtUtil = jwtUtil;
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
        Student student = studentMapper.studentResponseDTOToStudent(studentResponseDTO);

        studentRepository.findById(student.getId()).orElseThrow(() ->
                new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED)
        );

        if (student.getPassword() == null) {
            student.setPassword(studentRepository.findById(student.getId())
                    .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED))
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

    @Override
    public String registerStudent(String email) {
        Optional<Student> existingStudent = studentRepository.findByEmail(email);
        if (existingStudent.isPresent()) {
            throw new CustomException(ErrorNumber.EMAIL_IS_EXIST);
        }

        String otp = emailService.generateOTP();
        emailService.sendOTPEmail(email, otp);

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
            if (student.getOtpCode().equals(otp) && student.getTimeOtp().isAfter(LocalDateTime.now().minusMinutes(1))) {
                student.setVerifyStudent(true);
                studentRepository.save(student);
                return "OTP verified successfully!";
            } else {
                throw new CustomException(ErrorNumber.OTP_EXPIRED);
            }
        } else {
            throw new CustomException(ErrorNumber.EMAIL_NOT_EXISTED);
        }
    }

    @Override
    public Optional<StudentResponseDTO> getStudentLoginInfor() {
        Integer studentId = jwtUtil.extractStudentIdFromRequest(request);
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
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            throw new CustomException(ErrorNumber.PASSWORD_IS_NULL);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(newPassword);

        student.setPassword(hashedPassword);
        studentRepository.save(student);
    }
}
