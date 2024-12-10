package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String timestamp;
    private Integer errorNumber;

    public ErrorResponse(int status, String message, Integer errorNumber) {
        this.status = status;
        this.message = message;
        this.errorNumber = errorNumber;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

}
