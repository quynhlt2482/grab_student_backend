package backendgrabstudent.backend_GrabStudent.Exception;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomRuntimeException(CustomException ex) {
        ErrorNumber errorNumber = ex.getErrorNumber();
        ErrorResponse errorResponse = new ErrorResponse(
                errorNumber.getHttpStatus().value(),
                errorNumber.getMessage(),
                errorNumber.getCode()
        );
        return new ResponseEntity<>(errorResponse, errorNumber.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                500,
                "An unexpected error occurred: " + ex.getMessage(),
                9999
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
