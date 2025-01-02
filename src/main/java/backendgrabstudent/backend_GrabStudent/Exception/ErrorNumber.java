package backendgrabstudent.backend_GrabStudent.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorNumber {
    ACCOUNT_NOT_EXISTED(1, "Account doesn't exist", HttpStatus.NOT_FOUND),
    POST_NOT_EXISTED(2, "Post doesn't exist", HttpStatus.NOT_FOUND),
    TOKEN_EXPIRED(3, "Invalid or expired JWT token", HttpStatus.BAD_REQUEST),
    PASSWORD_IS_NULL(4, "Password is null", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EXISTED(5, "Mail doesn't exist", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD(6, "Invalid password", HttpStatus.BAD_REQUEST),
    RIDE_REQUEST_NOT_EXISTED(7, "Rider request doesn't exist", HttpStatus.NOT_FOUND),
    DRIVER_NOT_EXISTED(8, "Driver doesn't exist", HttpStatus.NOT_FOUND),
    PASSENGER_NOT_EXISTED(9, "Passenger doesn't exist", HttpStatus.NOT_FOUND),
    RIDE_NOT_EXISTED(10, "Ride doesn't exist", HttpStatus.NOT_FOUND),
    EMAIL_IS_EXIST(11, "Email doesn't exist", HttpStatus.BAD_REQUEST),
    OTP_EXPIRED(12, "OTP expired", HttpStatus.BAD_REQUEST),
    ID_STUDENT_NOT_FOUND_IN_TOKEN(13, "Student ID is missing in the JWT token", HttpStatus.NOT_FOUND),
    AUTHORITY_NOT_EXISTED(14, "Authorization header is missing or invalid", HttpStatus.NOT_FOUND),
    REFRESH_TOKEN_EXPIRED(15, "Refresh token expired", HttpStatus.BAD_REQUEST),
    POST_IS_CLOSED(16, "Post is closed", HttpStatus.BAD_REQUEST),
    INVALID_POST_TYPE(17, "INVALID_POST_TYPE", HttpStatus.BAD_REQUEST),
    INVALID_STATUS(18, "INVALID_STATUS", HttpStatus.BAD_REQUEST),
    RIDE_REQUEST_EXISTED(19, "RIDE_REQUEST_EXISTED", HttpStatus.BAD_REQUEST),
    RIDE_EXISTED(20, "RIDE_EXISTED", HttpStatus.BAD_REQUEST),
    SOMETHING_WENT_WRONG(21, "SOMETHING_WENT_WRONG", HttpStatus.BAD_REQUEST),
    RIDE_REVIEW_NOT_EXISTED(22, "review doesn't exist", HttpStatus.BAD_REQUEST),
    INVALID_TYPE(23, "INVALID TYPE", HttpStatus.BAD_REQUEST),
    NOTIFICATION_NOT_EXISTED(24, "NOTIFICATION doesn't exist", HttpStatus.NOT_FOUND),
    REVIEW_EXISTED(25, "REVIEW_EXISTED", HttpStatus.BAD_REQUEST),
    ;
    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
