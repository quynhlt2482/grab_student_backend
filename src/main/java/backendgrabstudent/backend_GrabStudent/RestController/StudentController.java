package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.StudentPasswordUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.VerifyOtpResponse;
import backendgrabstudent.backend_GrabStudent.Service.StudentService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
//    @PostMapping("/register")
//    public String register(@RequestParam String email) {
//        return studentService.registerStudent(email);
//    }

    // API để xác thực OTP
    @PostMapping("/verifyOtp")
    public ResponseObject<VerifyOtpResponse> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        var result = studentService.verifyOtp(email, otp);

        return ResponseObject.<VerifyOtpResponse>builder()
                .data(result)
                .build();
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
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student with ID " + id + " deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student with ID " + id + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the student.");
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody StudentResponseDTO studentResponseDTO) {
        StudentResponseDTO createdStudent = studentService.saveStudent(studentResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Student created with ID: " + createdStudent.getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody StudentResponseDTO studentResponseDTO) {
        if (!studentService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student with ID " + id + " not found");
        }

        studentResponseDTO.setId(id);
        studentService.updateStudent(studentResponseDTO);
        return ResponseEntity.ok("Student updated successfully");
    }
    @PutMapping("updatePassword/{id}")
    public ResponseEntity<String> updatePassword(
            @PathVariable int id,
            @RequestBody StudentPasswordUpdateDTO passwordUpdateDTO) {

        if (passwordUpdateDTO.getPassword() == null || passwordUpdateDTO.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must not be empty");
        }

        studentService.updatePassword(id, passwordUpdateDTO.getPassword());
        return ResponseEntity.ok("Password updated successfully");
    }

}
