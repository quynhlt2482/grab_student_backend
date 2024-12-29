package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.SendOtpResponse;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.VerifyOtpResponse;
import backendgrabstudent.backend_GrabStudent.Service.EmailService;
import backendgrabstudent.backend_GrabStudent.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // Endpoint để gửi OTP
    @PostMapping("/sendOtp")
    public ResponseObject<SendOtpResponse> sendOtp(@RequestParam String toEmail) {
        var result =  emailService.sendOTPEmail(toEmail);

        return ResponseObject.<SendOtpResponse>builder()
                .data(result)
                .build();
    }
}
