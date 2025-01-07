package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.StudentPasswordUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.LoginResponse;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentManagerReponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Mapper.StudentMapper;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import backendgrabstudent.backend_GrabStudent.Security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    public StudentServiceImple(StudentRepository studentRepository, StudentMapper studentMapper, EmailService emailService, HttpServletRequest request, JwtUtil jwtUtil) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.emailService = emailService;
        this.request = request;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Float getStudentRating(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));

        return student.getRating();
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
        studentRepository.deleteStudent(id);
    }

    @Override
    public Optional<StudentResponseDTO> getStudentLoginInfor() {
        Integer studentId = jwtUtil.extractStudentIdFromRequest(request);
        return studentRepository.findById(studentId)
                .map(studentMapper::studentToStudentResponseDTO);
    }

    @Override
    public void change2fa(int id, boolean isEnabled) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        student.setIs2faEnabled(isEnabled);
        studentRepository.save(student);
    }

    @Override
    public void updatePassword(int id, StudentPasswordUpdateDTO studentPasswordUpdateDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));

        if (!student.getPassword().equals(studentPasswordUpdateDTO.getPassword())) {
          throw new CustomException(ErrorNumber.INVALID_PASSWORD);
        }
        student.setPassword(studentPasswordUpdateDTO.getNewPassword());
        studentRepository.save(student);
    }

    @Override
    public LoginResponse verifyOtp(String email, String otp) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorNumber.EMAIL_NOT_EXISTED));

        // Tạo access token
        String accessToken = jwtUtil.generateToken(student.getEmail(), student.getId());

        if (student.getOtpCode().equals(otp) && student.getTimeOtp().isAfter(LocalDateTime.now().minusMinutes(5))) {
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .studentInfo(studentMapper.studentToStudentResponseDTO(student))
                    .build();
        } else {
            return LoginResponse.builder()
                    .accessToken(null)
                    .studentInfo(null)
                    .build();
        }
    }

    @Override
    public List<StudentManagerReponseDTO> getAllStudentManagerReponse() {
        return studentRepository.findAllStudent().stream()
                .map(studentMapper::studentToStudentManagerResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentManagerReponseDTO updateUserinManager(StudentManagerReponseDTO studentManagerReponseDTO) {
        Student student = studentMapper.studentManagerResponseDTOToStudent(studentManagerReponseDTO);

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
        return studentMapper.studentToStudentManagerResponseDTO(savedStudent);
    }



    //     Phương thức đăng ký tài khoản và gửi OTP
//    @Override
//    public String registerStudent(String email) {
//        Optional<Student> existingStudent = studentRepository.findByEmail(email);
//        if (existingStudent.isPresent()) {
//            return "Email already registered!";
//        }
//
//        // Gửi OTP và tạo bản ghi sinh viên mới
//        String otp = emailService.generateOTP();
//        emailService.sendOTPEmail(email, otp);
//
//        // Tạo bản ghi mới trong cơ sở dữ liệu
//        Student newStudent = new Student();
//        newStudent.setEmail(email);
//        newStudent.setOtpCode(otp);
//        newStudent.setTimeOtp(LocalDateTime.now());
//        newStudent.setVerifyStudent(false);
//        newStudent.setName("");
//        newStudent.setPassword("");
//        newStudent.setPhonenumber("");
//        studentRepository.save(newStudent);
//
//        return "OTP has been sent to " + email;
//    }
}
