package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.SendOtpResponse;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final StudentRepository studentRepository;

    @Autowired
    public EmailService(JavaMailSender mailSender, StudentRepository studentRepository) {
        this.mailSender = mailSender;
        this.studentRepository = studentRepository;
    }

    public SendOtpResponse sendOTPEmail(String toEmail) {
        try {
            Student student = studentRepository.findByEmail(toEmail)
                    .orElseThrow(() -> new RuntimeException("Email not found"));

            SecureRandom secureRandom = new SecureRandom();
            int otp = 100000 + secureRandom.nextInt(900000);
            String otpString = String.valueOf(otp);

            student.setOtpCode(otpString);
            student.setTimeOtp(LocalDateTime.now().plusMinutes(5));
            studentRepository.save(student);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otpString);

            mailSender.send(message);

            return new SendOtpResponse(otpString);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

