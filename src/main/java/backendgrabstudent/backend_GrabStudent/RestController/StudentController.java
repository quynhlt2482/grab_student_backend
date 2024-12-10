package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.StudentPasswordUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // API để đăng ký tài khoản và gửi OTP
    @PostMapping("/register")
    public String register(@RequestParam String email) {
        return studentService.registerStudent(email);
    }

    // API để xác thực OTP
    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return studentService.verifyOtp(email, otp);
    }

    @GetMapping("/All")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("/information")
    public Optional<StudentResponseDTO> getStudentLoginInfor() {
        return studentService.getStudentLoginInfor();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student with ID " + id + " deleted successfully.");
    }
    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody StudentResponseDTO studentResponseDTO) {
        StudentResponseDTO createdStudent = studentService.saveStudent(studentResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Student created with ID: " + createdStudent.getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody StudentResponseDTO studentResponseDTO) {
            studentResponseDTO.setId(id);
            studentService.updateStudent(studentResponseDTO);
            return ResponseEntity.ok("Student updated successfully");
    }
    @PutMapping("updatePassword/{id}")
    public ResponseEntity<String> updatePassword(
            @PathVariable int id,
            @RequestBody StudentPasswordUpdateDTO passwordUpdateDTO) {
        studentService.updatePassword(id, passwordUpdateDTO.getPassword());
        return ResponseEntity.ok("Password updated successfully");
    }

}
