package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.StudentDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Mapper.StudentMapper;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public StudentServiceImple(StudentRepository studentRepository, StudentMapper studentMapper, EmailService emailService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.emailService = emailService;
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        return studentRepository.findAll().stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> getStudentById(int id) {
        return studentRepository.findById(id)
                .map(studentMapper::studentToStudentDTO);
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOToStudent(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.studentToStudentDTO(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOToStudent(studentDTO);
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.studentToStudentDTO(updatedStudent);
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
}
