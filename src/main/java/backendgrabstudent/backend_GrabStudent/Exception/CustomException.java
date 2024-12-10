package backendgrabstudent.backend_GrabStudent.Exception;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorNumber errorNumber;

    public CustomException(ErrorNumber errorNumber) {
        super(errorNumber.getMessage());
        this.errorNumber = errorNumber;
    }
}
