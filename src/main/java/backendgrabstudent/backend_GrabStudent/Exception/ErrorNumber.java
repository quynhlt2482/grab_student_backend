package backendgrabstudent.backend_GrabStudent.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorNumber {
    ACCOUNT_NOT_EXISTED(1, "Account doesn't exist",HttpStatus.NOT_FOUND),
    POST_NOT_EXISTED(2, "Post doesn't exist",HttpStatus.NOT_FOUND),
    TOKEN_EXPIRED(3, "Token expired",HttpStatus.BAD_REQUEST),
    PASSWORD_IS_NULL(4, "Password is null",HttpStatus.BAD_REQUEST),
    ;
    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
